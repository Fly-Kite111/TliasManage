package com.example.tlias;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Tlias 教学管理系统启动类
 */
@SpringBootApplication
@MapperScan("com.example.tlias.mapper")
public class TliasApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasApplication.class, args);
    }
}
