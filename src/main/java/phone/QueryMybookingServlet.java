package phone;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import beans.Officialbooking;
import beans.Person;
import dao.OfficialbookingDao;
import dao.OfficialbookingDaoImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.WebServlet;
import beans.Mybooking;
import dao.MybookingDao;
import dao.MybookingDaoImpl;
import com.example.mytest.QRCodeGenerator;
import com.google.gson.Gson;
import system.SM4Util;

@WebServlet("/QueryMybookingServlet")
public class QueryMybookingServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("submitAction");
        String id = SM4Util.encrypt(request.getParameter("id-card"));
        String phoneNumber = SM4Util.encrypt(request.getParameter("phone"));
        if ("查看二维码".equals(action)) {
            // 查询按钮被点击，执行查询逻辑
            MybookingDao mybookingDao = new MybookingDaoImpl();
            OfficialbookingDao officialbookingDao = new OfficialbookingDaoImpl();
            Mybooking querymybooking;
            Officialbooking queryofficialbooking;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format(new java.util.Date());
            querymybooking = mybookingDao.query_find(id, request.getParameter("name"), phoneNumber);
            queryofficialbooking = officialbookingDao.query_find(id, request.getParameter("name"), phoneNumber);
            if (querymybooking == null && queryofficialbooking==null) {
//            String redirectUrl = request.getServletContext().getContextPath() + "/yanzhengma.jsp"; // 你原来的页面的URL
                // 如果没有查询到数据，可以做一些处理，比如返回错误信息或重定向到错误页面
                //传递给一个message变量
                request.setAttribute("message", "没有查询到数据！");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/yanzhengma.jsp");
                dispatcher.forward(request, response);
//            System.out.println("没有查询到数据！");
//            response.sendRedirect(redirectUrl);
            } else if(queryofficialbooking!=null && time.equals(queryofficialbooking.getIntime()) && queryofficialbooking.getPermit().equals("已审核")) {
                request.setAttribute("message", "查询到数据！");
                request.setAttribute("queryofficialbooking", queryofficialbooking);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/validtime_officialbooking.jsp");
                dispatcher.forward(request, response);
            } else if(querymybooking != null && time.equals(querymybooking.getIntime())){
                // 如果查询到数据，可以做一些处理，比如返回查询到的数据
                //传递给一个message变量
                request.setAttribute("message", "查询到数据！");
                request.setAttribute("querymybooking", querymybooking);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/validtime.jsp");
                dispatcher.forward(request, response);

            }
            else if(queryofficialbooking!=null){
                // 如果查询到数据，可以做一些处理，比如返回查询到的数据
                //传递给一个message变量
                request.setAttribute("message", "不在预约时间内！");
                request.setAttribute("queryofficialbooking", queryofficialbooking);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/invalidtime_officialbooking.jsp");
                dispatcher.forward(request, response);
            }
            else if(querymybooking != null){
                // 如果查询到数据，可以做一些处理，比如返回查询到的数据
                //传递给一个message变量
                request.setAttribute("message", "不在预约时间内！");
                request.setAttribute("querymybooking", querymybooking);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/invalidtime.jsp");
                dispatcher.forward(request, response);
            }
            System.out.println("查看二维码");
        } else if ("查询历史记录".equals(action)) {
            // 确认按钮被点击，执行确认逻辑
            MybookingDao mybookingDao = new MybookingDaoImpl();
            OfficialbookingDao officialbookingDao = new OfficialbookingDaoImpl();
            ArrayList<Mybooking> mybookings;
            ArrayList<Officialbooking> officialbookings;

            mybookings = mybookingDao.findAllQueryMybooking(id, request.getParameter("name"), phoneNumber);
            officialbookings = officialbookingDao.findAllQueryOfficialbooking(id, request.getParameter("name"), phoneNumber);
            request.setAttribute("mybookings", mybookings);
            request.setAttribute("officialbookings", officialbookings);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/chaxun.jsp");
            dispatcher.forward(request, response);
            System.out.println("查询历史记录");
        }
        else System.out.println("未知操作");

    }
}
