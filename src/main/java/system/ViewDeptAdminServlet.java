package system;

import java.util.*;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.AdminDAO;

@WebServlet("/viewDeptAdmin")
public class ViewDeptAdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDAO adminDAO = new AdminDAO();
        List<Admin> adminList = adminDAO.getAllAdmins();

//        for (Admin admin : adminList) {
//            System.out.println("Admin Name: " + admin.getName());
//            System.out.println("Login Name: " + admin.getLoginName());
//            System.out.println("Department ID: " + admin.getDepartmentName());
//            System.out.println("Phone: " + admin.getPhone());
//            System.out.println("Role: " + admin.getRole());
//            System.out.println("-------------------");
//        }

        String json = new Gson().toJson(adminList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

//        System.out.println("Admin List JSON: " + json);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
