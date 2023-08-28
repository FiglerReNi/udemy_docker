package com.example.applicationdockerfrommaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example","hu.docker"})
public class ApplicationDockerFromMavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationDockerFromMavenApplication.class, args);
    }

}
