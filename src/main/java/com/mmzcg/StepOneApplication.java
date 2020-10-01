package com.mmzcg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@MapperScan("com.mmzcg.mapper")
public class StepOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(StepOneApplication.class, args);
    }

}
