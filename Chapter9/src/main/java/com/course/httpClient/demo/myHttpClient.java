package com.course.httpClient.demo;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class myHttpClient {
@Test
    public void test() throws IOException {
        String result;
        HttpGet get = new HttpGet("http://www.baidu.com");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result =EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
}


}



//编写一个到百度网页的get请求思路
//1、创建一个result 用来存放结果
//2、new一个网址（百度）
//3、执行get请求 ，且获取get请求所有数据
//4、将获得的数据进行转码成String类型，并赋值给result
//5、打印出result结果
