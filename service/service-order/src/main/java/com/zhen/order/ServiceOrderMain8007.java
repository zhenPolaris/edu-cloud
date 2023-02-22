package com.zhen.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@MapperScan("com.zhen.order.mapper")
@ComponentScan("com.zhen")
@EnableDiscoveryClient //服务发现功能
public class ServiceOrderMain8007 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderMain8007.class,args);
    }
}
