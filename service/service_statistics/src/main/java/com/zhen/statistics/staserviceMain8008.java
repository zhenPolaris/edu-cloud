package com.zhen.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@MapperScan("com.zhen.statistics.mapper")
@EnableDiscoveryClient //开启服务发现
@ComponentScan("com.zhen")
@EnableScheduling
public class staserviceMain8008 {
    public static void main(String[] args) {
        SpringApplication.run(staserviceMain8008.class,args);
    }
}
