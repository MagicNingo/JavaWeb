package com.Exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestException {
    public String input() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        TestException t = new TestException();
        int[] arr;
        while (true) {
            System.out.println("请输入数组的长度：");
            String s = t.input();
            try {
                int length = Integer.parseInt(s);
                arr = new int[length];
                break;
            } catch (NumberFormatException e) {
                System.out.println("数组的长度和值只能为整数！");
            } catch (NegativeArraySizeException e) {
                System.out.println("数组的长度不能为负数！");
            }
        }

        for (int j = 0; j < arr.length; j++) {
            try {
                System.out.println("请输入"+j+"号位置的值：");
                arr[j] = Integer.parseInt(t.input());
            } catch (NumberFormatException e) {
                System.out.println("数组的值只能为整数！");
                j--;
            }
        }

        System.out.println("数组arr里面的值：");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();


    }
}
