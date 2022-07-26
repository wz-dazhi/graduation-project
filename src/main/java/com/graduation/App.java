package com.graduation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @projectName: graduation-project
 * @className: App
 * @description:
 * @author: yue
 * @date: 2022/6/13
 * @version: 1.0
 */
@MapperScan("com.graduation.mapper")
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
