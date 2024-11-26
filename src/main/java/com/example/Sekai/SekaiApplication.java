package com.example.Sekai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.Sekai.dao")
@EnableScheduling
public class SekaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SekaiApplication.class, args);
    }

}
