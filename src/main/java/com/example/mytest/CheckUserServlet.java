package com.example.mytest;
import java.io.*;

import beans.Customer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.WebServlet;
@WebServlet("/CheckUserServlet")
public class CheckUserServlet extends HttpServlet{
    String message = null;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String value1 = "", value2 = "";
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(int i = 0; i<cookies.length; i++){
                cookie = cookies[i];
                if(cookie.getName().equals("username")){
                    value1 = cookie.getValue();
                }
                if(cookie.getName().equals("password")){
                    value2 = cookie.getValue();
                }
            }
            if(value1.equals("admin") && value2.equals("admin")){
                message = "欢迎您！" + value1 + "再次登录该页面！";
                request.getSession().setAttribute("message", message);
                response.sendRedirect("GuessNumberServlet");
            }
            else{
                response.sendRedirect("login.jsp");
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        if(!username.equals("admin") || !password.equals("admin")){
            message = "用户名或密码错误！";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("login.jsp");
        }
        else{
            if((request.getParameter("check")!=null) && (request.getParameter("check").equals("check"))){
                Cookie nameCookie = new Cookie("username",username);
                Cookie pswdCookie = new Cookie("password",password);
                nameCookie.setMaxAge(60);
                pswdCookie.setMaxAge(60);
                response.addCookie(nameCookie);
                response.addCookie(pswdCookie);
            }
            message = "你已经成功登录！";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("GuessNumberServlet");
        }
    }

}
