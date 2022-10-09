package com.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication

public class ServerApplication {


    public static void main(String[] args) {
        System.out.println("Beta version 0.5");
        SpringApplication.run(ServerApplication.class, args);
    }

}
