package com.mongodb.kitchensink_migrated.service;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.entity.User;
import com.mongodb.kitchensink_migrated.exception.DuplicateEmailException;
import com.mongodb.kitchensink_migrated.repository.MemberRepository;
import com.mongodb.kitchensink_migrated.repository.UserRepository;
import com.mongodb.kitchensink_migrated.validator.MemberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberValidator memberValidator, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.memberRepository = memberRepository;
        this.memberValidator = memberValidator;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void register(Member member) {
        logger.info("Registering member with email: {}", member.getEmail());
        try {
            memberValidator.validate(member);
            if (memberRepository.findByEmail(member.getEmail()) != null) {
                logger.warn("Email already exists: {}", member.getEmail());
                throw new DuplicateEmailException("Email already exists: " + member.getEmail());
            }
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            User user = new User();
            user.setUsername(member.getUsername());
            user.setPassword(member.getPassword());

            memberRepository.save(member);
            userRepository.save(user);
            logger.info("Member registered successfully: {}", member.getEmail());
        } catch (Exception e) {
            logger.error("Error registering member: {}", member.getEmail(), e);
            throw e;
        }
    }

    public Member findById(Long id) {
        logger.info("Finding member by ID: {}", id);
        return memberRepository.findById(String.valueOf(id)).orElse(null);
    }

    public List<Member> findAll() {
        logger.info("Fetching all members");
        return memberRepository.findAllByOrderByUsernameAsc();
    }



}
