package system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.AdminDAO;

@WebServlet("/addAdmin")
public class AddAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前登录用户的Admin对象
        Admin currentAdmin = (Admin) request.getSession().getAttribute("admin");
        // 接收参数并打印调试信息
        String name = request.getParameter("name");
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        String departmentName = request.getParameter("departmentName");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");

        boolean success = false;
        String message = "";
        if(isValidPower(currentAdmin,role)) {
            if (isValidPassword(password)) {
                String encryptedPassword = SM3Util.encrypt(password);
                Admin admin = new Admin(name, loginName, encryptedPassword, departmentName, phone, role);
                AdminDAO adminDAO = new AdminDAO();
                success = adminDAO.addAdmin(admin);
                if (success) {
                    message = "管理员添加成功";
                } else {
                    message = "添加管理员时出错";
                }
            } else {
                message = "密码不符合要求";
            }
        }
        else{
            message = "您没有该权限";
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(new Result(success, message)));
        out.close();
    }

    private boolean isValidPower(Admin currentAdmin, String newUserRole) {
        if (currentAdmin == null || newUserRole == null) {
            return false;
        }

        String currentUserRole = currentAdmin.getRole();
        switch (currentUserRole) {
            case "系统管理员":
                return newUserRole.equals("学校管理员") || newUserRole.equals("审计管理员");
            case "学校管理员":
                return newUserRole.equals("部门管理员");
            default:
                return false;
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

    class Result {
        boolean success;
        String message;

        Result(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
    }
}


