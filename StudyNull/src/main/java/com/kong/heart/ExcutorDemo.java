package com.kong.heart;

public class ExcutorDemo {
    public static void main(String[] args) {
        //必须在线程执行处 处理异常，不然不会报异常，程序会很诡异
        Runnable r = ()->{
            try {
                throw new RuntimeException("xxxException");
            }catch (Exception e){
                e.printStackTrace();
            }
//            System.out.println("hello");
        };
        ExecutorUtil.scheduleAtFixedRate(r, -1, 1000);
    }
}
