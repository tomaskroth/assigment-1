package com.waes.differ.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.waes.differ.api"})
public class DifferAppAPI {

    public static void main(String[] args) {
        SpringApplication.run(DifferAppAPI.class, args);
    }

}
