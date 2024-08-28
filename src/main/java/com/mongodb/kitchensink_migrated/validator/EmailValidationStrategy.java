package com.mongodb.kitchensink_migrated.validator;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailValidationStrategy implements MemberValidationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(EmailValidationStrategy.class);

    @Override
    public void validate(Member member) throws InvalidMemberDataException {
        logger.info("Validating email for member: {}", member.getEmail());

        if (member.getEmail() == null || member.getEmail().isEmpty()) {
            String errorMessage = "Email cannot be null or empty";
            logger.error(errorMessage);
            throw new InvalidMemberDataException(errorMessage);
        }

        logger.info("Email validation passed for member: {}", member.getEmail());
    }
}
