package com.interfaces;

public class MainBoard implements Pci{
    private Pci[] pcis;

    public MainBoard(int num) {
        pcis = new Pci[num];
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        System.out.println("主板启动了！！");
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        System.out.println("主板关闭了！！");
    }

    public Pci[] getPcis() {
        return pcis;
    }

    public void setPcis(Pci...pcis) {
        this.pcis = pcis;
    }
}
