package com.mongodb.kitchensink_migrated.validator;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import org.springframework.stereotype.Component;

@Component
public class EmailValidationStrategy implements MemberValidationStrategy {
    @Override
    public void validate(Member member) throws InvalidMemberDataException {
        if (member.getEmail() == null || member.getEmail().isEmpty()) {
            throw new InvalidMemberDataException("Email cannot be null or empty");
        }
    }
}