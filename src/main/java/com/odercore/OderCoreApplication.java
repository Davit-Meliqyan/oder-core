package com.odercore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OderCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(OderCoreApplication.class, args);
    }

}
