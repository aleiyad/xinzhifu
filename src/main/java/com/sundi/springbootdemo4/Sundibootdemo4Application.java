package com.sundi.springbootdemo4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages={"com.sundi.springbootdemo4.mapper"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.sundi.springbootdemo4.repository")
@EntityScan(basePackages = "com.sundi.springbootdemo4.bean.persist")//
public class Sundibootdemo4Application {

    public static void main(String[] args) {
        SpringApplication.run(Sundibootdemo4Application.class, args);
    }

}
