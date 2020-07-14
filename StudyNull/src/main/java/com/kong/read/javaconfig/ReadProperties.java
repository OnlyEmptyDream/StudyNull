package com.kong.read.javaconfig;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
    public static void main(String[] args) {
        String path = PathDemo.class.getClassLoader().getResource("pathdemo.properties").getPath();
        readProperties(path);
    }

    public static void readProperties(String filePath){
        InputStream in = null;
        try {
            in = new FileInputStream(filePath);
            Properties pro = new Properties();
            pro.load(in);
            String demo = pro.getProperty("demo");
            System.out.println(demo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
