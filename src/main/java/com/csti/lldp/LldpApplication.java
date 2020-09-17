package com.csti.lldp;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages={"com.csti.*"})
@MapperScan(basePackages="com.csti",annotationClass= Mapper.class)
@EnableScheduling
public class LldpApplication {

    public static void main(String[] args) {
        SpringApplication.run(LldpApplication.class, args);
    }

}
