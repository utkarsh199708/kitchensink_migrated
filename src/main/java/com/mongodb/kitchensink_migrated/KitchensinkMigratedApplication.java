package com.mongodb.kitchensink_migrated;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.Cacheable;

@SpringBootApplication
@EntityScan(basePackages = "com.mongodb.kitchensink_migrated.entity")
@Cacheable
public class KitchensinkMigratedApplication {

    public static void main(String[] args) {
        SpringApplication.run(KitchensinkMigratedApplication.class, args);
    }

}
