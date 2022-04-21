package com.Servlet.Day0811;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/servlet01")
public class MyServlet01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //转发 ： 可以携带数据,内部跳转(保持原来的请求和响应不变)
        String msg = "大家好！";
        req.setAttribute("msg", msg);
        //req.getRequestDispatcher("./servlet02").forward(req,resp);

        //重定向：相当于一个新的请求 不能使用request的域去传递数据
        //默认情况下编码格式是：iso-8859-1
        String name = "张三";
        byte[] buf = name.getBytes("UTF-8");
        name = new String(buf,"iso-8859-1");
        resp.sendRedirect("./servlet02?username="+name);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
