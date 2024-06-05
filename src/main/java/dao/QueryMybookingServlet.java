package dao;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import beans.Person;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.WebServlet;
import beans.Mybooking;
import dao.MybookingDao;
import dao.MybookingDaoImpl;
import com.example.mytest.QRCodeGenerator;
import com.google.gson.Gson;

@WebServlet("/QueryMybookingServlet")
public class QueryMybookingServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MybookingDao mybookingDao = new MybookingDaoImpl();
        Mybooking querymybooking;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(new java.util.Date());
        querymybooking = mybookingDao.query_find(request.getParameter("id-card"), request.getParameter("name"), request.getParameter("phone"));
        if (querymybooking == null) {
//            String redirectUrl = request.getServletContext().getContextPath() + "/yanzhengma.jsp"; // 你原来的页面的URL
            // 如果没有查询到数据，可以做一些处理，比如返回错误信息或重定向到错误页面
            //传递给一个message变量
            request.setAttribute("message", "没有查询到数据！");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/yanzhengma.jsp");
            dispatcher.forward(request, response);
//            System.out.println("没有查询到数据！");
//            response.sendRedirect(redirectUrl);
        } else if(querymybooking != null && time.equals(querymybooking.getIntime())){
            // 如果查询到数据，可以做一些处理，比如返回查询到的数据
            //传递给一个message变量
            request.setAttribute("message", "查询到数据！");
            request.setAttribute("querymybooking", querymybooking);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/validtime.jsp");
            dispatcher.forward(request, response);

        }
        else{
            // 如果查询到数据，可以做一些处理，比如返回查询到的数据
            //传递给一个message变量
            request.setAttribute("message", "不在预约时间内！");
            request.setAttribute("querymybooking", querymybooking);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/invalidtime.jsp");
            dispatcher.forward(request, response);
        }
    }
}
