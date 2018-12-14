package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我所有post请求")
@RequestMapping(value = "v1")
public class myPostMethod {

    //这个变量是用来存放cookies的
    private  static  Cookie cookie;

    //用户登陆成功获取到cookies，然后再访问其他接口获取到列表
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口，成功后获得cookies信息")
  public String login(HttpServletResponse response,
                      @RequestParam(value = "username") String username,
                      @RequestParam(value = "password") String password){

      if (username.equals("zhangsan")&&password.equals("123456")){
          Cookie cookie = new Cookie("login","true");
          response.addCookie(cookie);
          return "登陆成功！";
      }
        return "用户名或密码错误";
  }

@RequestMapping(value = "/userList",method = RequestMethod.POST)
@ApiOperation(value = "获取用户列表",httpMethod = "POST")
public String getUserList(HttpServletRequest request, @RequestBody User u ){

      User user;
      //获取cookies
      Cookie[] cookies = request.getCookies();
      //验证cookies是否合法
      for (Cookie c : cookies){
          if (c.getName().equals("login") && c.getValue().equals("true") && u.getUsername().equals("zhangsan") && u.getPassword().equals("123456")){

              user = new User();
              user.setNsme("李四");
              user.setAge("18");
              user.setSex("man");
              return user.toString();
          }
      }
    return "参数不合法";
}



}
//body参数为中文jmeter请求时 提示参数不合法，必须要写成英文
//jmeter请求并查看cookies值
//线程组 ->http coolies管理器 ->http请求 ->debug simple ->查看结果树
//（在jmeter.properties中将CookieManager.save.cookies=false改为CookieManager.save.cookies=true）就可以在debug simple 中查看到cookies
//HTTP信息头管理器 ->http coolies管理器 ->http请求 ->debug simple ->查看结果树