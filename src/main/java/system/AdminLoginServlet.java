package system;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.*;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");

        // 加密密码
        String encryptedPassword = SM3Util.encrypt(password);

        // 验证登录
        AdminDAO adminDAO=new AdminDAO();
        Admin admin = adminDAO.login(loginName, encryptedPassword);
        if (admin != null) {
            request.getSession().setAttribute("admin", admin);
            response.sendRedirect("adminDashboard.jsp");
        } else {
            response.sendRedirect("login.jsp?error=invalid_credentials");
        }
    }
}

