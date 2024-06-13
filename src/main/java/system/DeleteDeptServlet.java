package system;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import dao.DeptDAO;

@WebServlet("/deleteDept")
public class DeleteDeptServlet extends HttpServlet {
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        String jsonString = jsonBuilder.toString();

        Gson gson = new Gson();
        DeleteDeptRequest deleteRequest = gson.fromJson(jsonString, DeleteDeptRequest.class);

        String deptId = deleteRequest.getDeptId();
        DeptDAO deptDAO = new DeptDAO();
        boolean success = deptDAO.deleteDeptById(deptId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (success) {
            response.getWriter().write("{\"success\": true}");
        } else {
            response.getWriter().write("{\"success\": false, \"message\": \"部门删除失败\"}");
        }
    }

    private static class DeleteDeptRequest {
        private String deptId;

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }
    }
}
