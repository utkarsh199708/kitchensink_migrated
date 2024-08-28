package com.mongodb.kitchensink_migrated.controller;



import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.entity.User;
import com.mongodb.kitchensink_migrated.exception.DuplicateEmailException;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import com.mongodb.kitchensink_migrated.repository.MemberRepository;
import com.mongodb.kitchensink_migrated.repository.UserRepository;
import com.mongodb.kitchensink_migrated.service.MemberService;
import com.mongodb.kitchensink_migrated.validator.MemberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MemberValidator memberValidator;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister_Success() {
        Member member = new Member();
        member.setUsername("testuser");
        member.setEmail("testuser@example.com");
        member.setPassword("password");

        when(memberRepository.findByEmail("testuser@example.com")).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn("encodedpassword");

        memberService.register(member);

        assertEquals("encodedpassword", member.getPassword());
    }

    @Test
    void testRegister_DuplicateEmailException() {
        Member member = new Member();
        member.setEmail("testuser@example.com");

        when(memberRepository.findByEmail("testuser@example.com")).thenReturn(member);

        assertThrows(DuplicateEmailException.class, () -> memberService.register(member));
    }

    @Test
    void testFindById_Success() {
        Member member = new Member();
        member.setId("1");
        when(memberRepository.findById("1")).thenReturn(Optional.of(member));

        Member result = memberService.findById(1L);
        assertNotNull(result);
        assertEquals("1", result.getId());
    }




    @Test
    void testFindAll_Success() {
        memberService.findAll();
    }


}
