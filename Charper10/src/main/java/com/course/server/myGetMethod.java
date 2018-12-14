package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
//restController这个注释意思：告诉application中的@ComponentScan，我是需要被你扫描的类
@Api(value = "/",description = "这是我所有的get请求")
public class myGetMethod {

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response) {
        //HttpServerletRequest 装请求信息的类
        //HttpServerletResponse 装响应信息的类
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);

        return "宠溺";
    }



    /*要求客户端携带cookies信息
    * 这是一个需要携带cookies才能访问的get请求
    */
    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies信息",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "你必须携带cookies信息来访问";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "这是一个需要携带cookies才能访问的get请求";
            }
        }
        return "你必须携带cookies信息来";
    }

    /*开发一个需要携带参数访问的get请求
      第一种实现方式：url：key=value&&key=value
      我们来模拟获取商品列表
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "开发一个需要携带参数访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("面",10);
        myList.put("饭",20);
        return myList;
    }
    /*
    第二种需要携带参数访问的get请求
    url:ip:port/get/with/param/10/20
     */

    @RequestMapping(value = "/get/with/param/{start}/{end}")
    @ApiOperation(value = "第二种需要携带参数访问的get请求",httpMethod = "GET")
    public Map getMyLiest(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer> mylist2 = new HashMap<>();
        mylist2.put("面",23);
        mylist2.put("饭",22);
        return mylist2;
    }

}
