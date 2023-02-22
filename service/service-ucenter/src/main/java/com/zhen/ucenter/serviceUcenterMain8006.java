package com.zhen.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication//取消数据源自动配置
@ComponentScan("com.zhen")
@MapperScan("com.zhen.ucenter.mapper")
@EnableDiscoveryClient
public class serviceUcenterMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(serviceUcenterMain8006.class,args);
    }
}
