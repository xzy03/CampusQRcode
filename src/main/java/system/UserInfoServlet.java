package system;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserInfoServlet", urlPatterns = {"/userInfo"})
public class UserInfoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        if (admin != null) {
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("userInfo.jsp").forward(request, response);
        } else {
            response.sendRedirect("adminlogin.jsp");
        }
    }
}
