package com.Listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


public class LoginUserListener implements HttpSessionAttributeListener{
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("有用户登录了！");
        ServletContext context = se.getSession().getServletContext();
        Object num = context.getAttribute("loginUser");

        if (num == null) {
            context.setAttribute("loginUser",1);
        } else {
            context.setAttribute("loginUser",((Integer) num) + 1);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("有用户退出登录了！");
        ServletContext context = se.getSession().getServletContext();
        Integer num = (Integer) context.getAttribute("loginUser");
        if (num > 0) {
            context.setAttribute("loginUser", num - 1);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {

    }
}
