// src/main/java/com/mongodb/kitchensink_migrated/repository/UserRepository.java
package com.mongodb.kitchensink_migrated.repository;

import com.mongodb.kitchensink_migrated.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}