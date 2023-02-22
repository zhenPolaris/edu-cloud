package com.zhen.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: zhen
 * @Create: 2023-02-08 15:49
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.zhen"})
@EnableDiscoveryClient
public class OssApplicationMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(OssApplicationMain8002.class, args);
    }
}
