package system;

import dao.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
    private static final int MAX_LOGIN_ATTEMPTS = 5;
    private static final int LOCK_TIME_MINUTES = 1;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");

        // 加密密码
        String encryptedPassword = SM3Util.encrypt(password);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String message = null;

        // 验证登录
        AdminDAO adminDAO=new AdminDAO();
        Admin admin2 = adminDAO.getAdminByLoginName(loginName);
        System.out.println(session.getAttribute("loginAttempts"));
        System.out.println(session.getAttribute("lockTimestamp"));
        if(admin2==null&&!isUserLocked(session)){
//            session.setAttribute("lockTimestamp", null);
//            System.out.println(session.getAttribute("lockTimestamp"));
            // 登录失败，增加登录尝试次数
            int loginAttempts = getLoginAttempts(session);
            if(loginAttempts>=5){
                loginAttempts=0;
            }
//            System.out.println(session.getAttribute("loginAttempts"));
            loginAttempts++;
            session.setAttribute("loginAttempts", loginAttempts);

            if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                // 达到最大失败次数，锁定账户
                lockUser(session);
            }
            message="用户名不存在";
            request.getSession().setAttribute("message", message);
            RequestDispatcher rd = request.getRequestDispatcher("/adminlogin.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
//            response.sendRedirect("adminlogin.jsp");
        }
        else{
            // 检查用户是否已被锁定
            if (isUserLocked(session)) {
                message="账户已被锁定，请30分钟后再试";
                request.getSession().setAttribute("message", message);
                RequestDispatcher rd = request.getRequestDispatcher("/adminlogin.jsp");
                try {
                    rd.forward(request, response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
//                response.sendRedirect("adminlogin.jsp");
            }else {
                // 没有被锁定，验证用户名和密码
                Admin admin = adminDAO.login(loginName, encryptedPassword);
//                String password = request.getParameter("password");
//                byte[] sm3 = CryptoSM3.hash(password.getBytes());
//                String sm = CryptoSM3.bytesToHexString(sm3);

                //用户名密码正确
                if (admin != null) {
                    // 登录成功，重置登录尝试次数
                    resetLoginAttempts(session);

                    request.getSession().setAttribute("admin", admin);
                    //先判断一下密码是否超过90天
                    LocalDate nowdate = LocalDate.now(); // get the current date
                    String ptime = admin.getPtime();
                    //将ptime解析成LocalDate类型
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(ptime, formatter);
                    if (Math.abs(ChronoUnit.DAYS.between(nowdate, date)) > 90) {
                        message = "90天以上需更换一次密码";
                        request.getSession().setAttribute("message", message);
                        response.sendRedirect("passwordchange.jsp");
                    } else {
                        //没超过90天直接进入
                        message = "欢迎登录校园通行码预约管理系统";
                        request.getSession().setAttribute("message", message);
                        request.getSession().setAttribute("admin", admin);
                        request.getSession().setAttribute("sucess", "1");

                        // 创建一个 Cookie 并设置其过期时间为7天
                        Cookie loginCookie = new Cookie("adminLogin", loginName);
                        loginCookie.setMaxAge(7 * 24 * 60 * 60); // 7天
                        response.addCookie(loginCookie);
                        response.sendRedirect("adminDashboard.jsp");
                    }
                } else {
                    // 登录失败，增加登录尝试次数
                    int loginAttempts = getLoginAttempts(session);
                    loginAttempts++;
                    session.setAttribute("loginAttempts", loginAttempts);

                    if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                        // 达到最大失败次数，锁定账户
                        lockUser(session);
                    }
                    message = "密码不正确";
                    request.getSession().setAttribute("message", message);
                    RequestDispatcher rd = request.getRequestDispatcher("/adminlogin.jsp");
                    try {
                        rd.forward(request, response);
                    } catch (ServletException e) {
                        throw new RuntimeException(e);
                    }
//                    response.sendRedirect("adminlogin.jsp");
                }
            }
        }

    }

    private int getLoginAttempts(HttpSession session) {
        Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");
        return loginAttempts != null ? loginAttempts : 0;
    }
    private void lockUser(HttpSession session) {
        // 设置锁定时间戳ms
        session.setAttribute("lockTimestamp", new Date(System.currentTimeMillis() + LOCK_TIME_MINUTES * 10 * 1000));
    }
    private boolean isUserLocked(HttpSession session) {
        Date lockTimestamp = (Date) session.getAttribute("lockTimestamp");
        if (lockTimestamp == null) {
            return false; // 没有锁定时间戳，说明没有被锁定
        }
        Date now = new Date();
        return now.before(lockTimestamp); // 如果当前时间小于锁定时间戳，说明仍处于锁定状态
    }
    private void resetLoginAttempts(HttpSession session) {
        session.removeAttribute("loginAttempts");
    }
}

