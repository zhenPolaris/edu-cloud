package com.zhen.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.zhen")
@MapperScan("com.zhen.cms.mapper")
public class Service_cms_Main9004 {
    public static void main(String[] args) {
        SpringApplication.run(Service_cms_Main9004.class,args);
    }
}
