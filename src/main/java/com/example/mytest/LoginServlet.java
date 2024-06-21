package com.example.mytest;
import java.io.*;

import beans.Customer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.WebServlet;

//@WebServlet(name = "loginServlet", urlPatterns = "/tester-login")
//public class LoginServlet extends HttpServlet {
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        if(username.equals("admin") && password.equals("admin")){
//            request.setAttribute("username", username);
//            RequestDispatcher rd = request.getRequestDispatcher("question.jsp");
//            try {
//                rd.forward(request, response);
//            } catch (ServletException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        else{
//            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//            try {
//                rd.forward(request, response);
//            } catch (ServletException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//}

@WebServlet(name = "loginServlet", urlPatterns = "/tester-login")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username.equals("admin") && password.equals("admin")){
            Customer customer = new Customer(username,password);
            ServletContext servletContext = request.getServletContext();
            servletContext.setAttribute("customer",customer);
            RequestDispatcher rd = request.getRequestDispatcher("jsp/question.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
//            response.sendRedirect("/mytest_war_exploded/jsp/question.jsp");

        }
        else{
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
