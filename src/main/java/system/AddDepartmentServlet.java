package system;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.*;

@WebServlet("/addDepartment")
public class AddDepartmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");

        int departmentId = 0;  // 这里得改，使得每个id都不同
        Department department = new Department(departmentId, type, name);
        DepartmentDAO departmentDAO=new DepartmentDAO();
        departmentDAO.addDepartment(department);

        response.sendRedirect("departmentList.jsp");
    }
}
