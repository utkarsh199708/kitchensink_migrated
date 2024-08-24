package com.mongodb.kitchensink_migrated.utility;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Configuration
public class Resources {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public EntityManager entityManager() {
        return em;
    }

    @Bean
    public Logger produceLog() {
        return Logger.getLogger(Resources.class.getName());
    }
}

