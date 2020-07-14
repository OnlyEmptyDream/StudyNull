package com.kong.heart;

public class ExcutorDemo {
    public static void main(String[] args) {
        Runnable r = ()->{
            System.out.println("hello");
        };
        ExecutorUtil.scheduleAtFixedRate(r, -1, 1000);
    }
}
