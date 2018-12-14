package com.course.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2  //加上这两个注释后 会自动读取配置文件 并知道读取的是配置文件
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return  new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).pathMapping("/").select().paths(PathSelectors.regex("/.*")).build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("我的接口文档").contact(new Contact("lidan","","496703362@qq.com"))
                .description("这是我的swagger生成的文档").version("1.0.0.0").build();
    }
}
