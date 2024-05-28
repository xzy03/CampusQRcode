package com.example.mytest;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "simpleTestServlet", urlPatterns = "/simple-test")
public class SimpleTestServlet extends HttpServlet{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        response.setCharacterEncoding("gb2312");
        PrintWriter out = response.getWriter();
        String quest1 = request.getParameter("quest1");
        String[] quest2 = request.getParameterValues("quest2");
        String[] quest3 = request.getParameterValues("quest3");
        String quest4 = request.getParameter("quest4").trim();

        int score = 0;
        if(quest1!=null && quest1.equals("1")){
            score += 25;
        }
        if(quest2!=null && quest2[0].equals("3")){
            score += 25;
        }
        if(quest3!=null && quest3.length==2 && quest3[0].equals("2") && quest3[1].equals(("3"))){
            score += 25;
        }
        if(quest4!=null && (quest4.equals("HttpServlet")||quest4.equals("jakarta.servlet.http.HttpServlet"))){
            score += 25;
        }
        out.println("<html><head>");
        out.println("<title>考试结果</title>");
        out.println("</head><body>");
        out.println("你的成绩是："+score+"分");
        out.println("</body></html>");

    }
}
