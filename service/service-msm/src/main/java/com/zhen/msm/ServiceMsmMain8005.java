package com.zhen.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: zhen
 * @Create: 2023-02-15 14:06
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源自动配置
@ComponentScan("com.zhen")
public class ServiceMsmMain8005 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMsmMain8005.class,args);
    }
}
