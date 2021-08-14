package com.alke.hfs.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * :
 * Alke
 * 2021-08-13 14:45
 */
@SpringBootApplication
@ComponentScan({"com.alke.hfs"})
public class ServiceCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCoreApplication.class, args);
    }
}
