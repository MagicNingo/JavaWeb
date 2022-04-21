package com.interfaces;

public class NetCard implements Pci{
    @Override
    public void start() {
        System.out.println("网卡启动了！");
    }

    @Override
    public void stop() {
        System.out.println("网卡关闭了！");

    }
}
