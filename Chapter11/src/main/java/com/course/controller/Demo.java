package com.course.controller;


        import com.course.model.User;
        import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import lombok.extern.log4j.Log4j;
        import org.mybatis.spring.SqlSessionTemplate;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import javax.naming.Name;

@Log4j
@RestController
@Api(value = "v1",description = "这是我的第一个版本的demo")
@RequestMapping(value = "v1")
public class Demo {

    //首先过去一个执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;


    @RequestMapping(value = "/getUserCount", method = RequestMethod.GET)
    @ApiOperation(value = "可以获取到用户数", httpMethod = "GET")
    public int getUserCount() {
        return template.selectOne("getUserCount");
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public int addUser(@RequestBody User  user) {
        return template.insert("addUser",user);
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public int updateUser(@RequestBody User user){
        return template.update("updateUser",user);
    }
    ;
    @RequestMapping(value = "/deleterUser",method = RequestMethod.GET)
    public int delUser(@RequestParam int age){
      return template.delete("deleterUser",age); //("deleterUser",age);中的deleterUser是数据库中的对应的id,这个名字可以随便取，但是要和mysql.xml中的id一致
    }
}

