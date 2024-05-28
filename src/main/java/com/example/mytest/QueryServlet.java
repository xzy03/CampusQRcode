package com.example.mytest;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.util.Date;

import beans.Person;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.WebServlet;

import javax.naming.Context;
import javax.sql.DataSource;
import javax.naming.*;

@WebServlet(name="QueryServlet",urlPatterns = "/QueryServlet")
public class QueryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection conn = null;
    DataSource dataSource;

    public void init() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/xiangzy_db_uni");
            conn = dataSource.getConnection();
            System.out.println("数据库连接成功！");
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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