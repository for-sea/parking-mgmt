package com.forsea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

@Configuration
@EnableSwagger2 // 开启Swagger
public class SwaggerConfig {
    // 注入Swagger的Docket实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // .enable(false) // 关闭Swagger
                .select()
                // 配置扫描接口：RequestHandlerSelectors
                // 扫描包：.basePackage("com.forsea.controller")
                // 扫描带注解的类：.withClassAnnotation(RestController.class)
                // 扫描带注解的方法：.withMethodAnnotation(GetMapping.class)
                .apis(RequestHandlerSelectors.basePackage("com.forsea.controller"))
                // 过滤路径
                // .paths()
                .build();
    }

    private ApiInfo apiInfo(){
        // 作者信息
        Contact contact = new Contact("For-Sea", "#", "forsea.xiao@foxmail.com");
        // API的基本信息
        /*return new ApiInfoBuilder()
                .title("停车场登记管理系统API文档")//标题
                .description("小学期项目")// API描述
                .version("1.0.0")//接口的版本
                .build();*/

        return new ApiInfo(
                "停车场登记管理系统API文档",
                "小学期项目",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
