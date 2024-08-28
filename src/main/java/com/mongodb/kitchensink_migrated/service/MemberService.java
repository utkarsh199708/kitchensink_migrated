// src/main/java/com/mongodb/kitchensink_migrated/service/MemberService.java
package com.mongodb.kitchensink_migrated.service;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.entity.User;
import com.mongodb.kitchensink_migrated.exception.DuplicateEmailException;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import com.mongodb.kitchensink_migrated.repository.MemberRepository;
import com.mongodb.kitchensink_migrated.repository.UserRepository;
import com.mongodb.kitchensink_migrated.validator.MemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    @Autowired
    public MemberService(MemberRepository memberRepository, MemberValidator memberValidator, PasswordEncoder passwordEncoder,  UserRepository userRepository) {
        this.memberRepository = memberRepository;
        this.memberValidator = memberValidator;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void register(Member member) {
        User user = new User();
        memberValidator.validate(member);
        if (memberRepository.findByEmail(member.getEmail()) != null) {
            throw new DuplicateEmailException("Email already exists: " + member.getEmail());
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        user.setUsername(member.getUsername() );
        user.setPassword(member.getPassword());



        memberRepository.save(member);
        userRepository.save(user);
    }

    public Member findById(Long id) {
        return memberRepository.findById(String.valueOf(id)).orElse(null);
    }

    public void update(Member member) {
        memberValidator.validate(member);
        memberRepository.save(member);
    }

    public void delete(Long id) {
        memberRepository.deleteById(String.valueOf(id));
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}