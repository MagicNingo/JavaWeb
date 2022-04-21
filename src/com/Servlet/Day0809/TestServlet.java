package com.Servlet.Day0809;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
@WebServlet("/test")
public class TestServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("TestServlet 被初始化了！");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("----test----");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Servlet注销了！");
    }
}
