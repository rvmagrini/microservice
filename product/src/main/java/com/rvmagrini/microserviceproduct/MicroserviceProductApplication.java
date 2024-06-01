package com.rvmagrini.microserviceproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceProductApplication.class, args);
    }

}
