package com.rebac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "com.rebac")
public class RebacApplication {

    public static void main(String[] args) {
        SpringApplication.run(RebacApplication.class, args);
    }

}
