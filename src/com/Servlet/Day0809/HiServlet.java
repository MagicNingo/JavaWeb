package com.Servlet.Day0809;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hiServlet")
public class HiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ServletContext对象
        ServletContext context = getServletContext();
        System.out.println("HiServlet----------->" + context);

        ServletConfig config = getServletConfig();
        String param = config.getInitParameter("MyServletParam");
        System.out.println("ServletParam-------->"+param);

        String v = context.getInitParameter("myParameter");
        System.out.println("得到全局配置参数："+ v);
        //得到项目中，任何资源的绝对路径
        //String path = context.getRealPath("WEN-INF/bbb.txt");
        //String path = context.getRealPath("aaa.txt");
        String path = context.getRealPath("WEN-INF/classes/bbb.txt");
        System.out.println(path);

        //域对象有四个----->四大作用域 （存储数据的地方）
//        context.setAttribute(, );

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
