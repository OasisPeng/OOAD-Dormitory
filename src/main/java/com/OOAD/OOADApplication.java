package com.OOAD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class OOADApplication {

    public static void main(String[] args) {
        SpringApplication.run(OOADApplication.class, args);
    }

}

