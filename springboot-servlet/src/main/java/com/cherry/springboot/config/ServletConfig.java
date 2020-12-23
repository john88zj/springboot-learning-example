package com.cherry.springboot.config;

import com.cherry.springboot.filter.SimpleFilter;
import com.cherry.springboot.servlet.SimpleServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletRegistration;
import java.util.*;

/**
 * @Description
 * @Author zhujun
 * @Email
 * @Date 2020/2/19  5:31 PM
 * @Version
 **/
@Configuration
public class ServletConfig {


    /**
     * 设置 请求编码
     * 需要配置 spring.http.encoding.enabled=false  才能是filter编码设置生效
     *
     * 另外一种方式直接使用 配置方式  ---推荐使用
     * #spring.http.encoding.charset=UTF-8
     * #spring.http.encoding.enabled=true
     * #spring.http.encoding.force=true
     * @return
     */
//    @Bean
//    public FilterRegistrationBean characterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setForceEncoding(true);
//        characterEncodingFilter.setEncoding("UTF-8");
//
//        filterRegistrationBean.setFilter(characterEncodingFilter);
//        filterRegistrationBean.addUrlPatterns("/*");
//        return filterRegistrationBean;
//    }

    @Bean
    public ServletRegistrationBean servletRegistration() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new SimpleServlet(), "/simpleServlet");

        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new SimpleFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }



}
