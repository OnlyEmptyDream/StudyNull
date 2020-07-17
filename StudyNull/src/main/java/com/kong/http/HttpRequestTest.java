package com.kong.http;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 纯java的http请求 不依赖任何jar包
 */
public class HttpRequestTest {
    String url = "www.baidu.com";

    public void doGet() {
        InputStream is = null;
        BufferedReader reader = null;
        try {
            //此处处理get请求数据
            url += "app_token="+url;
            URL dataUrl = new URL(url);

            HttpURLConnection con = (HttpURLConnection) dataUrl.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("content-type", "text/json");
            con.setRequestProperty("Proxy-Connection", "Keep-Alive");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setConnectTimeout(3000);// 设置3秒超时
            con.setReadTimeout(3000);// 设置3秒超时
            is = con.getInputStream();

            reader = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doPost(){
        try {
            URL dataUrl = new URL(url);

            HttpURLConnection con = (HttpURLConnection) dataUrl.openConnection();

            con.setRequestMethod("POST");

            con.setRequestProperty("Content-Type", "application/json");

            con.setRequestProperty("Proxy-Connection", "Keep-Alive");

            // con.setRequestProperty("receipt-data", receipt);
            con.setConnectTimeout(10000);// 设置3秒超时
            con.setReadTimeout(10000);// 设置3秒超时
            con.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());

            JSONObject body = new JSONObject();
//            = toJsonObject();  //最终包数据

            out.write(body.toString());
            out.flush();
            out.close();

            InputStream is = con.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(is));
            //               String allString = new String();
            String line = null;
            String resultStr = "";
            //               Hashtable<String,String> result = new Hashtable<String, String>();
            while((line = reader.readLine()) != null){
                resultStr += line;
            }
            //获得返回的json数据
            JSONObject resultObj;
//            JSONObject.fromObject(resultStr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
