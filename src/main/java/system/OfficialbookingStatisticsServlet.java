package system;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.*;

@WebServlet("/getStatisticsOfficial")
public class OfficialbookingStatisticsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applyMonth = request.getParameter("applyMonth");
        String reservationMonth = request.getParameter("reservationMonth");
        String campus = request.getParameter("campus");
        String department = request.getParameter("department");

        OfficialbookingDaoImpl officialbookingDAOImpl = new OfficialbookingDaoImpl();
        List<StatisticsOfficial> statistics = officialbookingDAOImpl.getStatistics(applyMonth, reservationMonth, campus, department);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(statistics));
    }
}
