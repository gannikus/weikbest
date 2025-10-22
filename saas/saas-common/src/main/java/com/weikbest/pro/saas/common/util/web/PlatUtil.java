package com.weikbest.pro.saas.common.util.web;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * @author wisdomelon
 * @date 2019/7/27 0027
 * @project saas
 * @jdk 1.8
 * 查找经纬度
 */
public class PlatUtil {

    /**
     * 接口调用 GET
     */
    public static String httpURLConectionGET(String key, String city, String address) throws Exception {

        String geturl = "http://restapi.amap.com/v3/geocode/geo?key=" + key + "&s=rsv3&city=" + city + "&address=" + address;

        // 把字符串转换为URL请求地址
        URL url = new URL(geturl);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        // 获取输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        // 循环读取流
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        connection.disconnect();

        //System.out.println("Get=="+sb.toString());
        JsonParser jp = new JsonParser();
        //将json字符串转化成json对象
        JsonObject jo = jp.parse(sb.toString()).getAsJsonObject();
        String location = jo.get("geocodes").getAsJsonArray().get(0).getAsJsonObject().get("location").toString();
        //System.out.println("Get=="+location);

        return location;

    }

    /**
     * 接口调用  POST
     */
    public static void httpURLConnectionPOST() {
        try {
            URL url = new URL("http://地址");

            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            // 此时cnnection只是为一个连接对象,待连接中
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);

            // 设置连接输入流为true
            connection.setDoInput(true);

            // 设置请求方式为post
            connection.setRequestMethod("POST");

            // post请求缓存设为false
            connection.setUseCaches(false);

            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);

            // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
            // application/x-javascript text/xml->xml数据
            //application/x-javascript->json对象
            //application/x-www-form-urlencoded->表单数据
            //这是重点^_^
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();

            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
            //URLEncoder.encode()方法  为字符串进行编码
            String parm = "storeId=" + URLEncoder.encode("32", "utf-8");

            // 将参数输出到连接
            dataout.writeBytes(parm);

            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

            System.out.println(connection.getResponseCode());

            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            // 用来存储响应数据
            StringBuilder sb = new StringBuilder();

            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
                sb.append(bf.readLine());
            }
            // 重要且易忽略步骤 (关闭流,切记!)
            bf.close();
            // 销毁连接
            connection.disconnect();
            System.out.println("Post==" + sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //httpURLConectionGET();
        //httpURLConnectionPOST();
        System.out.println("222");
        String location = "\"100,20\"";
        System.out.println(location);
        location = location.replaceAll("\"", "");
        System.out.println(location);
    }

}
