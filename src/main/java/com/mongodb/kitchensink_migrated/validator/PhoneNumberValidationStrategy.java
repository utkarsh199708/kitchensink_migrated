package com.mongodb.kitchensink_migrated.validator;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberValidationStrategy implements MemberValidationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberValidationStrategy.class);

    @Override
    public void validate(Member member) throws InvalidMemberDataException {
        logger.debug("Starting phone number validation for member with ID: {}", member.getId());

        if (member.getPhoneNumber() == null || member.getPhoneNumber().isEmpty()) {
            String errorMessage = "Phone number cannot be null or empty";
            logger.error("Validation failed: {}", errorMessage);
            throw new InvalidMemberDataException(errorMessage);
        }

        logger.debug("Phone number validation successful for member with ID: {}", member.getId());
    }
}
