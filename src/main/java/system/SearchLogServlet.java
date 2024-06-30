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

@WebServlet("/searchLog")
public class SearchLogServlet extends HttpServlet {
    private final LogDao logDao = new LogDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        String userName = request.getParameter("userName");
        String userOperation = request.getParameter("userOperation");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        List<Auditlog> logs = logDao.searchLogs(userName, userOperation, startDate, endDate);
        String json = new Gson().toJson(logs);

        response.getWriter().write(json);
    }
}
