// src/main/java/com/mongodb/kitchensink_migrated/controller/MemberController.java
package com.mongodb.kitchensink_migrated.controller;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import com.mongodb.kitchensink_migrated.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/members")
@Validated
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@Valid @RequestBody Member member) {
        try {
            memberService.register(member);
            return ResponseEntity.ok("Member registered successfully");
        } catch (InvalidMemberDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error registering member: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        Member member = memberService.findById(id);
        return ResponseEntity.ok(member);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Long id, @Valid @RequestBody Member member) {
        try {
            memberService.update(member);
            return ResponseEntity.ok("Member updated successfully");
        } catch (InvalidMemberDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating member: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        try {
            memberService.delete(id);
            return ResponseEntity.ok("Member deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting member: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Member>> listAllMembers() {
        List<Member> members = memberService.findAll();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Member> getMemberByEmail(@PathVariable String email) {
        Member member = memberService.findByEmail(email);
        return ResponseEntity.ok(member);
    }
}