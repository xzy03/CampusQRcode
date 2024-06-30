//package listener;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletContext;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebListener;
//import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.HttpSessionListener;
//import jakarta.servlet.http.HttpSessionEvent;
//
//import java.util.ArrayList;
//
//@WebListener
//public class SessionListener implements HttpSessionListener {
//    private ServletContext context = null;
//    public void sessionCreated(HttpSessionEvent se) {
//        HttpSession session = se.getSession();
//        context = session.getServletContext();
//        ArrayList<HttpSession> sessionList = (ArrayList<HttpSession>) context.getAttribute("sessionList");
//        if (sessionList == null) {
//            sessionList = new ArrayList<HttpSession>();
//            context.setAttribute("sessionList", sessionList);
//        }else {
//            sessionList.add(session);
//        }
//        System.out.println("Session Created:: ID="+session.getId());
//    }
//    public void sessionDestroyed(HttpSessionEvent se) {
//        HttpSession session = se.getSession();
//        context = session.getServletContext();
//        ArrayList<HttpSession> sessionList = (ArrayList<HttpSession>) context.getAttribute("sessionList");
//        sessionList.remove(session);
//        String message = null;
//        message="登陆超时，请重新登陆";
//        session.setAttribute("message", message);
//        //怎么跳转到登陆页面
//
//        System.out.println("Session Destroyed:: ID="+session.getId());
//    }
//}
