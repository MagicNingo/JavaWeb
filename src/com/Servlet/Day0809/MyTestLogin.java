package com.Servlet.Day0809;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/myTest")
public class MyTestLogin implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext context = servletConfig.getServletContext();
        System.out.println("MyTestLogin------->" + context);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("----数据提交到MyTestLogin这里了 ----");
        //得到请求方式
        HttpServletRequest request = (HttpServletRequest) req;
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
        /*String queryString = request.getQueryString();
        System.out.println("get方式提交的数据:" + queryString);*/

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
       /* //1：得到相应的流对象
        BufferedReader reader = request.getReader();

        //2：再从流对象获取数据
        String str = null;
        while((str = reader.readLine()) !=null) {
            System.out.println("post方式得到的数据"+str);
        }*/

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
        }

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
