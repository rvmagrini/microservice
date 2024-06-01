package com.rvmagrini.microserviceinventory;

import com.rvmagrini.microserviceinventory.model.Inventory;
import com.rvmagrini.microserviceinventory.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceInventoryApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            inventoryRepository.save(Inventory.builder()
                    .skuCode("iPhone_13")
                    .quantity(100)
                    .build());

            inventoryRepository.save(Inventory.builder()
                    .skuCode("Pixel_7")
                    .quantity(0)
                    .build());
        };
    }

}
