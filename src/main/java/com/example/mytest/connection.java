package com.example.mytest;
import java.io.*;

import beans.Customer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "connection", urlPatterns = "/connection")
public class connection extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection conn = null;

    public void init() throws ServletException {
        String driver = "org.postgresql.Driver";
        String dburl = "jdbc:postgresql://192.168.239.128:26000/xiangzy_db_uni";
        String username = "zjutuser";
        String password = "zjutuser@123";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, username, password);
            System.out.println("数据库连接成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from xiangzy_students";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("xzy_sno") + "</td>");
                out.println("<td>" + rs.getString("xzy_sname") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}