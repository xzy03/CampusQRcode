package filter;

import beans.Auditlog;
import beans.Administrators;
import dao.LogDao;
import dao.LogDaoImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import system.Admin;

import java.io.*;


//@WebFilter(filterName = "LogFilter" ,urlPatterns = {"/searchMybooking"})
public class LogFilter extends HttpFilter {
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化操作，如果有的话
    }
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException,ServletException{
        System.out.println("进入过滤器");
        HttpSession session = request.getSession();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String uri = httpServletRequest.getRequestURI();
        String operation = determineOperation(uri);
        System.out.println("uri: " + uri);
        if (operation != null && !operation.equals("login")) {
            System.out.println("operation: " + operation);
            Admin myadmin=(Admin) session.getAttribute("admin");
            System.out.println("myadmin: " + myadmin);
            String uname = myadmin.getName();
            LogDaoImpl logDaoImpl = new LogDaoImpl();
            Auditlog log = new Auditlog();
            log.setUname(uname);
            log.setOperation(operation);
            log.setDescription("User accessed: " + uri);
            try{
                logDaoImpl.addLog(log);
            }catch(Exception se) {
                se.printStackTrace();
            }
        }
        else if(operation != null && operation.equals("login")){
            String  uname = httpServletRequest.getParameter("loginName");
            LogDaoImpl logDaoImpl = new LogDaoImpl();
            Auditlog log = new Auditlog();
            log.setUname(uname);
            log.setOperation(operation);
            log.setDescription("User accessed: " + uri);
            try{
                logDaoImpl.addLog(log);
            }catch(Exception se) {
                se.printStackTrace();
            }
        }
        chain.doFilter(request, response);

        //设置到过滤器的行为
        //addlog
        //转发？if (operation != null && userId != -1) {
        //            logService.logOperation(userId, operation, "User accessed: " + uri);
        //        }
        //
        //        chain.doFilter(request, response);
    }
    private String determineOperation(String uri) {
        if (uri.contains("/addAdmin")) {
            return "addAdmin";
        }
        else if(uri.contains("/addDepartment")){
            return "addDepartment";
        } else if (uri.contains("/addDept")) {
            return "addDept";
        }
        else if (uri.contains("/adminLogin")) {
            return "login";
        } else if (uri.contains("/adminLogout")){
            return "logout";
        } else if (uri.contains("/deleteAdmin")) {
            return "deleteAdmin";
        } else if (uri.contains("/deleteDept")) {
            return "deleteDept";
        }
        else if(uri.contains("/searchAdmin")){
            return "searchAdmin";
        }
        else if(uri.contains("/searchAppointments")){
            return "searchAppointments";
        }
        else if(uri.contains("/searchDept")){
            return "searchDept";
        }
        else if(uri.contains("/searchMybooking")){
            return "searchMybooking";
        }
        else if(uri.contains("/searchOfficialbooking")){
            return "searchOfficialbooking";
        }
        else if(uri.contains("/updateAdmin")){
            return "updateAdmin";
        }
        else if(uri.contains("/updateDept")){
            return "updateDept";
        }
        else if(uri.contains("/updateOfficialBooking")){
            return "updateOfficialBooking";
        }
        else if(uri.contains("/viewDept")){
            return "viewDept";
        }
        else if(uri.contains("/viewDeptAdmin")){
            return "viewDeptAdmin";
        }
        else if(uri.contains("/searchLog")){
            return "searchLog";
        }
        return null;
    }
    public void destroy() {
        // 清理操作（如果需要）
    }
}
