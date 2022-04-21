package com.TestProgram;

import com.bus.entity.Admin;
import com.bus.service.AdminService;
import com.bus.service.AdminServiceImp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {
    public static void main(String[] args) {
        AdminServiceImp asi = new AdminServiceImp();
        //as就是我们通过动态代理得到的代理对象
        AdminService as = (AdminService)Proxy.newProxyInstance(
                //第一个参数：类加载器
                TestProxy.class.getClassLoader(),
                //第二个参数：目标对象的实现的接口
                asi.getClass().getInterfaces(),
                //第三个参数：InvocationHandler的实例
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("-------事务处理！-----");
                        //这里就是调用目标对象的原生方法
                        Object obj = method.invoke(asi, args);
                        System.out.println("-----事务处理结束！-----");
                        System.out.println("关闭资源！");
                        return obj;
                    }
                }
        );
        as.deleteAdminByID(1);

    }
}
