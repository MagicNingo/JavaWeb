package com.Servlet.Day0813;

import com.bus.entity.Admin;
import com.bus.service.AdminServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
        AdminServiceImp ad;

        public AdminLoginServlet() {
            ad = new AdminServiceImp();
        }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request:在这里可以直接使用,是因为request是jsp的内置对象
        // response:也是内置对象
        // out:也是内置对象
        // session:也是内置对象: session的值存在于一次会话当中
        /*request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");*/
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int time = Integer.parseInt(request.getParameter("time"));

        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        int i = ad.findAdminByLogin(new Admin(username, password));
        if (i == 1) {
            System.out.println("登录成功!");
            //把登录成功的用户名设置到session当中
            session.setAttribute("loginName", username);

            Cookie[] arr = request.getCookies();
            for (Cookie c : arr) {
                if ("JSESSIONID".equals(c.getName())) {
                    if(time == 1) {
                        c.setMaxAge(60*30);
                        session.setMaxInactiveInterval(60*30);
                    }else if (time == 2){
                        c.setMaxAge(60*60);
                        session.setMaxInactiveInterval(60*60);
                    } else if (time == 3) {
                        c.setMaxAge(60*60*24);
                        session.setMaxInactiveInterval(60*60*24);
                    } else {
                        c.setMaxAge(60*60*240);
                        session.setMaxInactiveInterval(60*60*240);
                    }
                    response.addCookie(c);
                }
            }
            //让页面弹出框出现相关信息，本质上是设置响应体
            out.write("<script>alert('恭喜你登录成功!'); location.href='AdminManagement.jsp'</script>");
        } else if (i == 0) {
            System.out.println("用户名错误!");
            out.write("<script>alert('输入的用户名错误!'); history.back()</script>");
        } else {
            System.out.println("密码错误!");
            out.write("<script>alert('输入的密码错误!'); history.back()</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
