package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 开启热部署
 * settings-Advanced settings-compiler
 * ctrl+F9快捷键实现热加载
 * @author HP
 */
@SpringBootApplication
@MapperScan("com.example.dao")
public class MusicAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicAppApplication.class, args);
    }

}
