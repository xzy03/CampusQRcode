package system;

import com.google.gson.Gson;
import dao.DeptDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/searchDept")
public class SearchDeptServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptId = request.getParameter("deptId");

        DeptDAO deptDAO = new DeptDAO();
        Dept dept=deptDAO.getDeptById(deptId);
        List<Dept> depts = new ArrayList<>();
        depts.add(dept);

        String json = new Gson().toJson(depts);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
