package com.zoho.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ZohoCrmApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZohoCrmApplication.class, args);
    }
}
