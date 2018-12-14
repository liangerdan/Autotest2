package com.course.httpClient.demo;


import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ResourceBundle;
public class myCookiesForPost {

    private String url;
    private ResourceBundle bundle;
    //未完成
    @BeforeTest
    public void beforeTest() {
        //获取配置文件中本地url
        bundle = ResourceBundle.getBundle("application");
        url = bundle.getString("test.url");
    }

    @Test
    public void getCookies() throws IOException {
        String result;
        //从配置文件中获取拼接地址
        String uri = bundle.getString("getCookies.uri");
        String URLtest = this.url + uri;
        //创建一个get请求
        HttpGet get = new HttpGet(URLtest);
        //创建一个客户端
        DefaultHttpClient client = new DefaultHttpClient();
        //执行请求
        HttpResponse response = client.execute(get);
        //将获取的结果赋值给result
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);


        //获取cookies的信息
        CookieStore store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("name=" + name + "   value=" + value);
        }


    }

    @Test(dependsOnMethods = {"getCookies"})
    public void  postCookies() throws UnsupportedEncodingException {
        //从配置中获取拼接url
        String uri =bundle.getString("post.with.cookies.uri");
        String urlpost=this.url+uri;

        //创建一个客户端
        HttpClient client = new DefaultHttpClient();
        //声明一个post方法
            HttpPost post = new HttpPost("urlpost");
        //添加参数
        JSONObject param=new JSONObject();
        param.put("name","huhansan");
        param.put("age",18);
        //设置请求头信息
        post.setHeader("Content-type","applection/json");
        //将参数信息添加到方法
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;





    }


}



