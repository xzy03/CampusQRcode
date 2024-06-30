package system;

import beans.Auditlog;
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
import dao.*;

@WebServlet("/viewLog")
public class ViewLogServlet extends HttpServlet {
    private final LogDao logDao = new LogDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        System.out.println("到servlet");
        List<Auditlog> logs = logDao.searchLogs("", "", null, null);
        String json = new Gson().toJson(logs);

        response.getWriter().write(json);
    }
}