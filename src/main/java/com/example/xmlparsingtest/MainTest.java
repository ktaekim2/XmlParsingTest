package com.example.xmlparsingtest;

public class MainTest {
    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("전달되는 argumets 의 갯수가  3개 이상이어야 합니다.");
            return;
        }

        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }
}
