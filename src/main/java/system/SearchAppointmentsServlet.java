package system;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import  java.util.*;
import dao.*;

@WebServlet("/searchAppointments")
public class SearchAppointmentsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicantName = request.getParameter("applicantName");
        String applicantId = request.getParameter("applicantId");
        String applicationDate = request.getParameter("applicationDate");
        String appointmentDate = request.getParameter("appointmentDate");
        String campus = request.getParameter("campus");
        AppointmentDAO appointmentDAO=new AppointmentDAO();
        List<Appointment> appointments = appointmentDAO.searchAppointments(applicantName, applicantId, applicationDate, appointmentDate, campus);
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("appointmentList.jsp").forward(request, response);
    }
}
