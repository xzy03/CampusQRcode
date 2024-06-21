package system;

import com.google.gson.Gson;
import dao.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateAdmin")
public class UpdateAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前登录用户的Admin对象
        Admin currentAdmin = (Admin) request.getSession().getAttribute("admin");
        String loginName = request.getParameter("loginName");
        String name = request.getParameter("name");
        String departmentName = request.getParameter("departmentName");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        AdminDAO adminDAO = new AdminDAO();
        Admin oldadmin = adminDAO.getAdminByLoginName(loginName);
        String oldrole=oldadmin.getRole();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(isValidPower(currentAdmin,oldrole,role)) {
            Admin admin = new Admin(name, loginName, null, departmentName, phone, role,null);
            boolean success = adminDAO.updateAdmin(admin);
            String message = success ? "管理员信息更新成功" : "更新管理员信息时出错";
            out.write(new Gson().toJson(new Result(success, message)));
        }
        else{
            // 如果没有权限
            String message = "没有权限更新该管理员信息";
            out.write(new Gson().toJson(new Result(false, message)));
        }
        out.close();
    }

    private boolean isValidPower(Admin currentAdmin, String oldUserRole, String newUserRole) {
        if (currentAdmin == null || oldUserRole == null || newUserRole == null) {
            return false;
        }

        String currentUserRole = currentAdmin.getRole();
        switch (currentUserRole) {
            case "系统管理员":
                return (newUserRole.equals("学校管理员") || newUserRole.equals("审计管理员")) && (oldUserRole.equals("学校管理员") || oldUserRole.equals("审计管理员"));
            case "学校管理员":
                return newUserRole.equals("部门管理员") && oldUserRole.equals("部门管理员");
            default:
                return false;
        }
    }

    class Result {
        boolean success;
        String message;

        Result(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
    }
}
