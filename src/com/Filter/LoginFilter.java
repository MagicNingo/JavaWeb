package com.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Object loginName = session.getAttribute("loginName");
        if (loginName != null) {
            filterChain.doFilter(request, servletResponse);
        } else {
            //应该放行不需要登录的资源 AdminLogin.jsp, CSS, JavaScript, Image,AdminLoginServlet
            String uri = request.getRequestURI();
            System.out.println("path--->"+uri +"---->"+(request.getContextPath()));
            //一下情况都应该放行
            if (uri.contains("AdminLogin-New")||uri.contains("CSS")||uri.contains("JavaScript")||uri.contains("Image")
               ||uri.contains("AdminLoginServlet")||uri.equals(request.getContextPath()+"/")) {
                filterChain.doFilter(request, servletResponse);
            } else {
                servletResponse.getWriter().write("<script>alert('请您先进行登录！'); location.href = 'AdminLogin-New.jsp'</script>");
            }
        }

    }
}
