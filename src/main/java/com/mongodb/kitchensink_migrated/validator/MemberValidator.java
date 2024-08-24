// src/main/java/com/mongodb/kitchensink_migrated/validator/MemberValidator.java

package com.mongodb.kitchensink_migrated.validator;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberValidator {

    private final List<MemberValidationStrategy> validationStrategies;

    @Autowired
    public MemberValidator(List<MemberValidationStrategy> validationStrategies) {
        this.validationStrategies = validationStrategies;
    }

    public void validate(Member member) throws InvalidMemberDataException {
        for (MemberValidationStrategy strategy : validationStrategies) {
            strategy.validate(member);
        }
    }
}