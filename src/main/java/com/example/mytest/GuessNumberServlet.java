package com.example.mytest;
import java.io.*;

import beans.Customer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.WebServlet;
@WebServlet("/GuessNumberServlet")
public class GuessNumberServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        int magic = (int)(Math.random()*101);
        HttpSession session = request.getSession();
        session.setAttribute("num",new Integer(magic));
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(session.getAttribute("message")==null?"":session.getAttribute("message"));
        out.println("<br>");
        out.println("我想出一个0到100之间的数，请你猜!");
        out.println("<form action='GuessNumberServlet' method='post'>");
        out.println("<input type='text' name='guess'/>");
        out.println("<input type='submit' value='确定'/>");
        out.println("</form>");
        out.println("</body></html>");
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        int guess = Integer.parseInt(request.getParameter("guess"));
        HttpSession session = request.getSession();
        int magic = (Integer)session.getAttribute("num");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if(guess==magic){
            session.invalidate();
            out.println("祝贺你，答对了!");
            out.println("<a href = 'GuessNumberServlet'>再猜一次</a>");
        }
        else if(guess>magic){
            out.println("太大了！请重猜！");
        }
        else{
            out.println("太小了！请重猜！");
        }
        out.println("<form action='GuessNumberServlet' method='post'>");
        out.println("<input type='text' name='guess'/>");
        out.println("<input type='submit' value='确定'/>");
        out.println("</form>");
        out.println("</body></html>");
    }
}
