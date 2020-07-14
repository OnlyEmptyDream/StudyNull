package com.kong.read.javaconfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.Map;

public class ReadYml {

    public static void main(String[] args) {
        String path2 = ReadYml.class.getResource("/").getPath() + "/application.yml";
        readYml(path2);
    }

//  key:
//      aa:
//          bb: 10

//    引入了两个maven依赖 1: fastjson  2: snakeyaml
    public static void  readYml(String filePath) {
        try {
            Yaml ymal = new Yaml();
            Map<Object, Object> ymlMap = ymal.load(new FileInputStream(filePath));

            JSONArray jsonArrays=(JSONArray) JSON.toJSON(ymlMap.values());
            for(int i = 0; i < 1; i++){
                Object obj = jsonArrays.getJSONObject(i).get("aa");
                JSONObject jsonObject1 = (JSONObject)JSON.toJSON(obj);


                System.out.println(jsonObject1.get("cc"));
                System.out.println();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
