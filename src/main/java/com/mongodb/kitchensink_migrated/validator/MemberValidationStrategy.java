package com.mongodb.kitchensink_migrated.validator;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;

public interface MemberValidationStrategy {
    void validate(Member member) throws InvalidMemberDataException;
}