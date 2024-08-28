package com.mongodb.kitchensink_migrated.validator;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberValidator {

    private static final Logger logger = LoggerFactory.getLogger(MemberValidator.class);

    private final List<MemberValidationStrategy> validationStrategies;

    @Autowired
    public MemberValidator(List<MemberValidationStrategy> validationStrategies) {
        this.validationStrategies = validationStrategies;
    }

    public void validate(Member member) throws InvalidMemberDataException {
        logger.info("Starting validation for member: {}", member.getEmail());

        for (MemberValidationStrategy strategy : validationStrategies) {
            logger.info("Applying validation strategy: {}", strategy.getClass().getSimpleName());
            try {
                strategy.validate(member);
            } catch (InvalidMemberDataException e) {
                logger.error("Validation failed using strategy: {} with error: {}", strategy.getClass().getSimpleName(), e.getMessage());
                throw e; // Re-throw the exception to ensure it's handled properly
            }
        }

        logger.info("Validation completed for member: {}", member.getEmail());
    }
}
