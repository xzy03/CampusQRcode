package system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.*;
import beans.*;

@WebServlet("/searchMybooking")
public class SearchMybookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applyTime = request.getParameter("applyTime");
        String entryTime = request.getParameter("entryTime");
        String campus = request.getParameter("campus");
        String unit = request.getParameter("unit");
        String name = request.getParameter("name");
        String idCard = request.getParameter("idCard");

        MybookingDaoImpl mybookingDaoImpl = new MybookingDaoImpl();
        List<Mybooking> bookings = mybookingDaoImpl.searchBookings(applyTime, entryTime, campus, unit, name, idCard);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(bookings));
        out.flush();
        //打印出当前界面的URL
        System.out.println("URL: " + request.getRequestURL());
    }
}
