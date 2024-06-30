package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//@WebFilter("/*") // 拦截所有请求
public class SessionTimeoutFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入会话过滤器");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false); // 不创建新的会话
        System.out.println(session.getAttribute("success"));
        if (session == null) {
            System.out.println("session为空");
            // 会话已超时或未登录，重定向到登录页面
            String message = null;
            message="登陆超时，请重新登陆";
            request.setAttribute("message2", message);
            RequestDispatcher rd = request.getRequestDispatcher("/adminlogin.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        } else {
            // 会话有效，继续处理请求
            chain.doFilter(request, response);
        }
    }

    // ... 其他方法 ...
}