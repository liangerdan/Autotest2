import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.course")
public class application {
    public static void main(String arg[]) {
        SpringApplication.run(application.class, arg);
    }
}
//application是一个入口。通过compomentScan注释将类托管给注释SpringBootApplication,然后给到application方法
//创建入口后 -》创建一个包(com.course.server)，写一个类 -》
