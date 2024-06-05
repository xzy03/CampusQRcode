package filter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter(filterName = "FormFilter", urlPatterns = {"/QueryMybookingServlet"})
//设置编码格式

public class QueryMybookingFilter extends HttpFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化操作，如果有的话
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 检查表单提交是否为空
        String name = request.getParameter("name");
        String idCard = request.getParameter("id-card");
        String phone = request.getParameter("phone");

        if (name == null || name.isEmpty() || idCard == null || idCard.isEmpty() || phone == null || phone.isEmpty()) {
            // 如果有任何一个字段为空，可以做一些处理，比如返回错误信息或重定向到错误页面
            request.setAttribute("message", "请填写完整信息！");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/yanzhengma.jsp");
            dispatcher.forward(request, response);

        } else {
            // 如果都不为空，继续执行后续的过滤器或Servlet
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // 过滤器销毁时的操作，如果有的话
    }
}