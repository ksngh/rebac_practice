package com.rebac;

import org.springframework.boot.SpringApplication;

public class TestRebacApplication {

    public static void main(String[] args) {
        SpringApplication.from(RebacApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
