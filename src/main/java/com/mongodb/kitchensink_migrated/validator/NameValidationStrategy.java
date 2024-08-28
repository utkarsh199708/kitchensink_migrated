package com.mongodb.kitchensink_migrated.validator;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import org.springframework.stereotype.Component;

import static com.mongodb.kitchensink_migrated.validator.ValidationConstants.NAME_PATTERN;

@Component
public class NameValidationStrategy implements MemberValidationStrategy {

    @Override
    public void validate(Member member) throws InvalidMemberDataException {
        if (member.getUsername() == null || member.getUsername().isEmpty()) {
            throw new InvalidMemberDataException("Name cannot be null or empty");
        }
        if (!member.getUsername() .matches(NAME_PATTERN)) {
            throw new InvalidMemberDataException("Name must contain only alphabetic characters");
        }
    }
}