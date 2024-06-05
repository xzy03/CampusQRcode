package system;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.AdminDAO;

@WebServlet("/addAdmin")
public class AddAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");

        // 加密密码
        String encryptedPassword = SM3Util.encrypt(password);

        Admin admin = new Admin(name, loginName, encryptedPassword, departmentId, phone, role);

        // 创建 AdminDAO 实例并调用 addAdmin 方法
        AdminDAO adminDAO = new AdminDAO();
        adminDAO.addAdmin(admin);

        response.sendRedirect("adminList.jsp");
    }
}
