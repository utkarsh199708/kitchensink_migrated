package com.mongodb.kitchensink_migrated.controller;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import com.mongodb.kitchensink_migrated.service.MemberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/members")
@Validated
@CrossOrigin(origins = "http://localhost:8081")

public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@Valid @RequestBody Member member) {
        try {
            logger.info("Registering new member with email: {}", member.getEmail());
            memberService.register(member);
            logger.info("Member registered successfully: {}", member.getEmail());
            return ResponseEntity.ok("Member registered successfully");
        } catch (InvalidMemberDataException e) {
            logger.warn("Invalid member data: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error registering member: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Error registering member: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        logger.info("Fetching member with ID: {}", id);
        Member member = memberService.findById(id);
        if (member != null) {
            logger.info("Member found: {}", member.getEmail());
        } else {
            logger.warn("Member not found with ID: {}", id);
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(member);
    }


    @GetMapping
    public ResponseEntity<List<Member>> listAllMembers() {
        logger.info("Fetching all members");
        List<Member> members = memberService.findAll();
        logger.info("Number of members found: {}", members.size());
        return ResponseEntity.ok(members);
    }


}
