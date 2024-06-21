package system;

import com.google.gson.Gson;
import dao.AdminDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/UpdateAdminPassword")
public class UpdateAdminPasswordServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前登录用户的Admin对象
        Admin currentAdmin = (Admin) request.getSession().getAttribute("admin");
        String newpassword = request.getParameter("change");
        String newpassword2 = request.getParameter("test");
        String message_check = null;
        if(!newpassword.equals(newpassword2)){
            message_check = "两次输入的密码不一致";
            request.getSession().setAttribute("message_check", message_check);
            RequestDispatcher rd = request.getRequestDispatcher("/passwordchange.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
        if (isValidPassword(newpassword)) {
            String encryptedPassword = SM3Util.encrypt(newpassword);
            currentAdmin.setPassword(encryptedPassword);
            AdminDAO adminDAO = new AdminDAO();
            boolean success = adminDAO.updateAdminPassword(currentAdmin);
            if (success) {
                request.getSession().setAttribute("admin", currentAdmin);
                request.getSession().setAttribute("message", "密码修改成功,请重新登录");
                RequestDispatcher rd = request.getRequestDispatcher("/adminlogin.jsp");
                try {
                    rd.forward(request, response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/passwordchange.jsp");
                try {
                    rd.forward(request, response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            message_check = "密码不符合要求";
            request.getSession().setAttribute("message_check", message_check);
            RequestDispatcher rd = request.getRequestDispatcher("/passwordchange.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) return false;

        boolean hasDigit = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChar = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) hasDigit = true;
            else if (Character.isUpperCase(c)) hasUpperCase = true;
            else if (Character.isLowerCase(c)) hasLowerCase = true;
            else hasSpecialChar = true;
        }

        return hasDigit && hasUpperCase && hasLowerCase && hasSpecialChar;
    }

}
