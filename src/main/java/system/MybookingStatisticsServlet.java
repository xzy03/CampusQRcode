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

@WebServlet("/getStatisticsPublic")
public class MybookingStatisticsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applyMonth = request.getParameter("applyMonth");
        String reservationMonth = request.getParameter("reservationMonth");
        String campus = request.getParameter("campus");

        MybookingDaoImpl mybookingDaoImpl = new MybookingDaoImpl();
        List<StatisticsPublic> statistics = mybookingDaoImpl.getStatistics(applyMonth, reservationMonth, campus);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(statistics));
    }
}
