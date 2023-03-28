package com.fc.yuntieba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
@MapperScan("com.fc.yuntieba.mapper")
public class YuntiebaApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuntiebaApplication.class, args);
    }

}
