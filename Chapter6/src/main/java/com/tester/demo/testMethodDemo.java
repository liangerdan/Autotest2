package com.tester.demo;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class testMethodDemo {

    @Test
    public  void  test1(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void  test2(){
        Assert.assertEquals(1,1);
    }

    @Test
    public  void  test3(){
        Assert.assertEquals(2,3);
    }

    @Test
    public  void  logDemo(){
        Reporter.log("这是自己打印的日志");
        throw new RuntimeException("这是我自己抛出的异常");
    }
}
