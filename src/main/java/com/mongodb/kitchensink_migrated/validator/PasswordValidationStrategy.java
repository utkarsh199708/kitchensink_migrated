package com.mongodb.kitchensink_migrated.validator;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.mongodb.kitchensink_migrated.validator.ValidationConstants.UPPERCASE_PATTERN;
import static com.mongodb.kitchensink_migrated.validator.ValidationConstants.SPECIAL_CHARACTER_PATTERN;

@Component
public class PasswordValidationStrategy implements MemberValidationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(PasswordValidationStrategy.class);

    @Override
    public void validate(Member member) throws InvalidMemberDataException {
        String password = member.getPassword();
        logger.info("Starting password validation for member: {}", member.getUsername());

        if (password == null || password.isEmpty()) {
            logger.error("Validation failed: Password cannot be null or empty");
            throw new InvalidMemberDataException("Password cannot be null or empty");
        }
        if (password.length() < 8) {
            logger.error("Validation failed: Password must be at least 8 characters long");
            throw new InvalidMemberDataException("Password must be at least 8 characters long");
        }
        if (!password.matches(UPPERCASE_PATTERN)) {
            logger.error("Validation failed: Password must contain at least one uppercase letter");
            throw new InvalidMemberDataException("Password must contain at least one uppercase letter");
        }
        if (!password.matches(SPECIAL_CHARACTER_PATTERN)) {
            logger.error("Validation failed: Password must contain at least one special character");
            throw new InvalidMemberDataException("Password must contain at least one special character");
        }

        logger.info("Password validation successful for member: {}", member.getUsername());
    }
}
