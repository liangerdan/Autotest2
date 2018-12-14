package com.course.httpClient.demo;

import com.sun.deploy.util.SessionState;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.params.CookieSpecPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class myCookiesForGet {

    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        //从配置文件中获取本地url
        bundle = ResourceBundle.getBundle("application",Locale.CHINA);
         url = bundle.getString("test.url");

    }


    @Test
    public  void getCookies() throws IOException {
        String result;
        //从配置文件中获取拼接url
        String uri = bundle.getString("getCookies.uri");
        String testurl = this.url+uri;
        //测试代码逻辑书写
        HttpGet get = new HttpGet(testurl);
        //HttpClient client = new DefaultHttpClient();  //①
        DefaultHttpClient client = new DefaultHttpClient();//②
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"UTF-8");
        System.out.println(result);

        //获取cookies的信息
      //  CookieStore store=client.getCookieStore(); //②
        this.store = client.getCookieStore(); //③
        List<Cookie> Cookielist =store.getCookies();
        for(Cookie cookie:Cookielist){
            String name = cookie.getName();
            String value= cookie.getValue();
            System.out.println("name="+name+";value="+value);
        }

        }


    @Test(dependsOnMethods = {"getCookies"})
    public  void test2() throws IOException {

         String uri1 = bundle.getString("get.with.cookies.uri");
         String urltest1=this.url+uri1;

        HttpGet get = new HttpGet(urltest1);
        DefaultHttpClient client = new DefaultHttpClient();
         client.setCookieStore( this.store);
         HttpResponse response = client.execute(get);


        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode ==200){
          String  result = EntityUtils.toString(response.getEntity(),"UTF-8");
            System.out.println(result);
        }
    }
}
//moco框架加HttpClient框架
// ①获取返回结果：先创建 ->获取本地地址 ->HttpClient逻辑处理
//②获取cookie值：先创建 ->获取本地地址 ->HttpClient逻辑处理 ->获取cookie值
//③         ：先创建 ->获取本地地址 ->。。->设置cookie值 ->测试逻辑书写 ->响应状态码



