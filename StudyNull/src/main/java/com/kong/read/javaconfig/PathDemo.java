package com.kong.read.javaconfig;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class PathDemo {
    public static void main(String[] args) {
        PathDemo pathDemo = new PathDemo();
        pathDemo.demo();
    }

    public void demo(){
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //如果放到其他工程中  这个地址也是其他工程的地址 而不是文件的绝对地址
        System.out.println(System.getProperty("user.dir"));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        String path = PathDemo.class.getClassLoader().getResource("pathdemo.properties").getPath();
        System.out.println(path);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        readProperties(path);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        String path2 = this.getClass().getResource("/").getPath() + "/application.yml";
        readYml(path2);
    }

    public void readProperties(String filePath){
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

    public  void  readYml(String filePath) {
        try {
            Yaml ymal = new Yaml();
            Map<Object, Object> ymlMap = ymal.load(new FileInputStream(filePath));
//            for(Object key:ymlMap.keySet()){
//                System.out.println(ymlMap.get(key).toString());
//            }

//            JSONArray jsonArray = new JSONArray((List<Object>) ymlMap.);
//            System.out.println();
            JSONArray jsonArrays=(JSONArray) JSON.toJSON(ymlMap.values());
            for(int i=0;i<1;i++){
                Object obj = jsonArrays.getJSONObject(i).get("aa");
                JSONObject jsonObject1=(JSONObject)JSON.toJSON(obj);
                System.out.println(jsonObject1.get("cc"));
                System.out.println();
            }
            System.out.println();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
