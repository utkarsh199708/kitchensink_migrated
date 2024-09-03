package com.mongodb.kitchensink_migrated.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Data
@Document(collection = "members")
public class Member implements Serializable {
    public static final String SEQUENCE_NAME = "members_sequence";

    @Id
    private Integer id;

    public @NotNull @Size(min = 1, max = 25) @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull @Size(min = 1, max = 25) @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers") String username) {
        this.username = username;
    }

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Indexed(unique = true)
    private String username;

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull
    @NotEmpty
    @Email
    @Indexed(unique = true) // Replaces @UniqueConstraint in JPA
    private String email;

    @NotNull
    @Size(min = 10, max = 12)
    @Digits(fraction = 0, integer = 12)
    private String phoneNumber;


}
