package com.cherry.platform.springbootswagger.config;

import com.cherry.platform.springbootswagger.annotation.NotIncludeSwagger;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;


/**
 * @Description
 * @Author zhujun
 * @Email
 * @Date 2020/4/28  10:12 AM
 * @Version
 **/

@EnableSwagger2
@Component
@Configuration
public class SwaggerConfig {



    @Value("${server.servlet.context-path}")
    private String pathMapping;

    @Bean
    public ApiInfo initApiInfo(){
//        return new ApiInfo("springboot-swagger api",
//                initContextInfo(),
//                "1.0.0",
//                "http://localhost:8081"+pathMapping+"/swagger-ui.html",
//                "后台开发团队",
//                "The Apache License, Version 2.0",
//                "http://localhost:8081"+pathMapping+"/swagger-ui.html"
//
//        );
        return new ApiInfoBuilder()
                .title("springboot-swagger api")
                .description(initContextInfo())
                .version("1.0,0")
                .termsOfServiceUrl("http://localhost:8081"+pathMapping+"/swagger-ui.html")
                .contact("后台开发团队")
                .license("https://swagger.io/")
                .licenseUrl("https://swagger.io/")
                .build();
    }


    private String initContextInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("REST API 设计在细节上有很多自己独特的需要注意的技巧，并且对开发人员在构架设计能力上比传统 API 有着更高的要求。")
                .append("<br/>")
                .append("本文通过翔实的叙述和一系列的范例，从整体结构，到局部细节，分析和解读了为了提高易用性和高效性，REST API 设计应该注意哪些问题以及如何解决这些问题。");

        return sb.toString();
    }


    @Bean
    public Docket restfulApi(){
        System.out.println("http://localhost:8081" + pathMapping + "/swagger-ui.html");
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .apiInfo(initApiInfo())
//                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(true)
//                .forCodeGeneration(false)
//                .pathMapping(pathMapping) // base，最终调用接口后会和paths拼接在一起
                .select()
                .apis(not(RequestHandlerSelectors.withMethodAnnotation(NotIncludeSwagger.class)))//排除掉含有 NotIncludeSwagger注解的方法
                .apis(RequestHandlerSelectors.basePackage("com.cherry.platform.springbootswagger.controller"))
//                .paths(doFilteringRules())
                .paths(PathSelectors.any())
                .build();

    }


    /**
     * 设置过滤规则
     * 这里的过滤规则支持正则匹配
     * @return
     */
    private Predicate<String> doFilteringRules() {
        return or(
                regex("/swagger/.*")//路径是/swagger/开始的
        );
    }
}
