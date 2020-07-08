package com.ss.ssproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class SsprojApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsprojApplication.class, args);
    }

}
