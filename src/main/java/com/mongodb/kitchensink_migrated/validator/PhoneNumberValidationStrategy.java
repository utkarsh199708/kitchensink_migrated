package com.mongodb.kitchensink_migrated.validator;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberValidationStrategy implements MemberValidationStrategy {
    @Override
    public void validate(Member member) throws InvalidMemberDataException {
        if (member.getPhoneNumber() == null || member.getPhoneNumber().isEmpty()) {
            throw new InvalidMemberDataException("Phone number cannot be null or empty");
        }
    }
}