package com.mongodb.kitchensink_migrated.validator;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import org.springframework.stereotype.Component;

import static com.mongodb.kitchensink_migrated.validator.ValidationConstants.UPPERCASE_PATTERN;
import static com.mongodb.kitchensink_migrated.validator.ValidationConstants.SPECIAL_CHARACTER_PATTERN;

@Component
public class PasswordValidationStrategy implements MemberValidationStrategy {

    @Override
    public void validate(Member member) throws InvalidMemberDataException {
        String password = member.getPassword();
        if (password == null || password.isEmpty()) {
            throw new InvalidMemberDataException("Password cannot be null or empty");
        }
        if (password.length() < 8) {
            throw new InvalidMemberDataException("Password must be at least 8 characters long");
        }
        if (!password.matches(UPPERCASE_PATTERN)) {
            throw new InvalidMemberDataException("Password must contain at least one uppercase letter");
        }
        if (!password.matches(SPECIAL_CHARACTER_PATTERN)) {
            throw new InvalidMemberDataException("Password must contain at least one special character");
        }
    }
}