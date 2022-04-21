package com.TestProgram;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestArrayList {
    public static void main(String[] args) {
        ArrayList<Double> list = new ArrayList<>();
//        LinkedList<Double> list = new LinkedList<>();
        long t1 = System.currentTimeMillis();
        for (double i = 1; i <= 10000000; i++) {
            list.add(i);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        long t3 = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }
        long t4 = System.currentTimeMillis();
        System.out.println(t4-t3);
    }
}
