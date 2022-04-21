package com.Listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineUserListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //当会话建立时，会产生session对象，就会触发listener的方法
        System.out.println("有用户上线了！");
        ServletContext context = se.getSession().getServletContext();
        Object num = context.getAttribute("onlineUser");
        if (num == null) {
            context.setAttribute("onlineUser", 1);
        } else {
            context.setAttribute("onlineUser", ((Integer) num) + 1);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("有用户下线了！");
        ServletContext context = se.getSession().getServletContext();
        Integer num = (Integer) context.getAttribute("onlineUser");
        if (num > 0) {
            context.setAttribute("onlineUser", num - 1);
        }
    }
}
