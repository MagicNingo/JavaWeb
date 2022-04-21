package com.Servlet.Day0809;


import com.bus.entity.Admin;
import com.bus.service.AdminServiceImp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post提交数据乱码的问题
        request.setCharacterEncoding("UTF-8");
        //方式一:通过响应头来设置响应体的编码格式(原生方式)
        //response.setCharacterEncoding("UTF-8");
       //response.setHeader("Content-Type", "text/html;charset=UTF-8");
        //方式二：简化方式
        response.setContentType("text/html;charset=UTF-8");

        AdminServiceImp as = new AdminServiceImp();
        /*ServletContext context = getServletContext();
        System.out.println("LoginServlet"+context);*/

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("获得的用户名和密码："+username +"----->"+password);
        int admin = as.findAdminByLogin(new Admin(username, password));

        //使用响应对象，设置响应体(打印字符流)
        PrintWriter out = response.getWriter();

        //输出字节流
        //ServletOutputStream outputStream = response.getOutputStream();

       /* if (admin == 1) {
            request.setAttribute("loginName", username);
            request.getRequestDispatcher("./AdminManagement.jsp").forward(request, response);
            //out.write("<script>alert('恭喜你登录成功'); location.href='AdminManagement.jsp'</script>");
        }else if (admin == -1){
            out.write("<script>alert('密码错误!'); location.href='AdminLogin.jsp'</script>");
        }else {
            out.write("<script>alert('用户名错误！'); location.href='AdminLogin.jsp'</script>");
        }*/

        /*if (name.equals(username) && pass.equals(password)) {
            System.out.println("登录成功！");
            //页面的跳转有两种方式： 转发 重定向
            //转发 ： 可以携带数据,内部跳转(保持原来的请求和响应不变)

            request.setAttribute("loginName", username);
            request.getRequestDispatcher("./AdminManagement.jsp").forward(request, response);
        }*/

        /*  System.out.println("----数据提交到LoginServlet这里了 ----");
        //得到请求方式
        String method = request.getMethod();
        System.out.println("请求方式:" + method);
        //得到项目路径(虚拟路径)
        String path = request.getContextPath();
        System.out.println("得到虚拟路径:" + path);
        //得到协议版本号
        String protocol = request.getProtocol();
        System.out.println("得到协议版本号:" + protocol);
        //得到Servlet路径
        String servletPath = request.getServletPath();
        System.out.println("得到Servlet路径:" + servletPath);

        //得到get方式的参数
        *//*String queryString = request.getQueryString();
        System.out.println("get方式提交的数据:" + queryString);*//*

        //得到请求的url ,uri
        String uri = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("uri:" + uri);
        System.out.println("URL:" + requestURL);

        //得到客户机的IP地址
        String addr = request.getRemoteAddr();
        System.out.println("addr:" + addr);


        System.out.println("----------===================------------");
        //得到请求头的信息
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            System.out.println(names.nextElement());
        }

        //根据请求头的名字得到相应的数据
        String header = request.getHeader("sec-ch-ua");
        System.out.println("请求头:" + header);

        System.out.println("----------===================------------");
        //post方式，得数据分两步
       */
        /* //1：得到相应的流对象
        BufferedReader reader = request.getReader();

        //2：再从流对象获取数据
        String str = null;
        while((str = reader.readLine()) !=null) {
            System.out.println("post方式得到的数据"+str);
        }*/
        /*

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("get和post通用的方式:"+"username&password="+username+"&"+password);

        //得到多个参数
        String[] strs = request.getParameterValues("hobit");

        //得到所有参数名
        Enumeration<String> arr = request.getParameterNames();
        while (arr.hasMoreElements()){
            System.out.println("参数名:"+arr.nextElement());
        }
        //得到所有的参数map集合
        Map<String, String[]> map = request.getParameterMap();
        Set<Map.Entry<String, String[]>> set = map.entrySet();
        for (Map.Entry<String, String[]> entry : set) {
            System.out.println(entry);
        }*/
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
