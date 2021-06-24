package com.example.computernetdesign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.computernetdesign.mapper")
@SpringBootApplication
public class ComputerNetDesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputerNetDesignApplication.class, args);
    }

}
