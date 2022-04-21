package com.Exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TestList {
    public static String input() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String s;
        ArrayList<Object> list = new ArrayList<>();
        while (true) {
            System.out.println("请输入：");
            s = input();
            list.add(s);
            if ("exit".equalsIgnoreCase(s)) {
                break;
            }
        }

        while (true) {
            System.out.println("请输入需要删除的几号位置：");
            s = input();
            int i = Integer.parseInt(s);
            list.remove(i);
            break;
        }

        for (Object o : list) {
            System.out.println(o);
        }

    }
}
