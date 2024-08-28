package com.mongodb.kitchensink_migrated.validator;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.mongodb.kitchensink_migrated.validator.ValidationConstants.NAME_PATTERN;

@Component
public class NameValidationStrategy implements MemberValidationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(NameValidationStrategy.class);

    @Override
    public void validate(Member member) throws InvalidMemberDataException {
        logger.info("Starting name validation for member: {}", member.getUsername());

        if (member.getUsername() == null || member.getUsername().isEmpty()) {
            logger.error("Validation failed: Name cannot be null or empty");
            throw new InvalidMemberDataException("Name cannot be null or empty");
        }
        if (!member.getUsername().matches(NAME_PATTERN)) {
            logger.error("Validation failed: Name '{}' must contain only alphabetic characters", member.getUsername());
            throw new InvalidMemberDataException("Name must contain only alphabetic characters");
        }

        logger.info("Name validation successful for member: {}", member.getUsername());
    }
}
