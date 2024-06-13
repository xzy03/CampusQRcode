package system;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.google.gson.Gson;
import java.io.BufferedReader;
import dao.AdminDAO;

@WebServlet("/deleteAdmin")
public class DeleteAdminServlet extends HttpServlet {
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前登录用户的Admin对象
        Admin currentAdmin = (Admin) request.getSession().getAttribute("admin");
        BufferedReader reader = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        String jsonString = jsonBuilder.toString();

        Gson gson = new Gson();
        DeleteAdminRequest deleteRequest = gson.fromJson(jsonString, DeleteAdminRequest.class);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String loginName = deleteRequest.getLoginName();
        AdminDAO adminDAO = new AdminDAO();
        Admin admin=adminDAO.getAdminByLoginName(loginName);
        String role =admin.getRole();
        if(isValidPower(currentAdmin,role)) {
            boolean success = adminDAO.deleteAdminByLoginName(loginName);
            if (success) {
                response.getWriter().write("{\"success\": true}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"管理员删除失败\"}");
            }
        }
        else{
            response.getWriter().write("{\"success\": false, \"message\": \"你没有权限\"}");
        }
    }

    private boolean isValidPower(Admin currentAdmin, String UserRole) {
        if (currentAdmin == null || UserRole == null) {
            return false;
        }

        String currentUserRole = currentAdmin.getRole();
        switch (currentUserRole) {
            case "系统管理员":
                return UserRole.equals("学校管理员") || UserRole.equals("审计管理员");
            case "学校管理员":
                return UserRole.equals("部门管理员");
            default:
                return false;
        }
    }

    private static class DeleteAdminRequest {
        private String loginName;

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }
    }
}
