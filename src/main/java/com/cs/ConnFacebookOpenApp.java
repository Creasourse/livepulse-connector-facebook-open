package com.cs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Facebook 连接器应用主类
 * 同步 Facebook Ads 广告投放数据，支持广告系列、广告组、广告和洞察数据
 *
 * @author LivePulse
 * @version 1.8
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@MapperScan("com.cs.mapper")
public class ConnFacebookOpenApp {

    public static void main(String[] args) {
        SpringApplication.run(ConnFacebookOpenApp.class, args);
    }
}
