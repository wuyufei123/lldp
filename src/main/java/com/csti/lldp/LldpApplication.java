package com.csti.lldp;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.csti.*"})
@MapperScan(basePackages="com.csti",annotationClass= Mapper.class)
public class LldpApplication {
    public static void main(String[] args) {
        SpringApplication.run(LldpApplication.class, args);
    }
}
