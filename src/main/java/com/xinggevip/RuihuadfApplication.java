package com.xinggevip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xinggevip.dao")
@SpringBootApplication
public class RuihuadfApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuihuadfApplication.class, args);
    }

}
