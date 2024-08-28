package com.mongodb.kitchensink_migrated.controller;

import com.mongodb.kitchensink_migrated.entity.Member;
import com.mongodb.kitchensink_migrated.exception.InvalidMemberDataException;
import com.mongodb.kitchensink_migrated.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MemberControllerTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    void testRegisterMember_Success() throws Exception {
        mockMvc.perform(post("/members/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testuser\", \"password\": \"password\", \"email\": \"testuser@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Member registered successfully"));
    }

    @Test
    void testRegisterMember_InvalidMemberDataException() throws Exception {
        doThrow(new InvalidMemberDataException("Invalid data")).when(memberService).register(any(Member.class));

        mockMvc.perform(post("/members/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testuser\", \"password\": \"password\", \"email\": \"testuser@example.com\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid data"));
    }

    @Test
    void testGetMember_Success() throws Exception {
        Member member = new Member();
        member.setId("1");
        member.setUsername("testuser");
        member.setEmail("testuser@example.com");

        when(memberService.findById(1L)).thenReturn(member);

        mockMvc.perform(get("/members/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"));
    }

    @Test
    void testUpdateMember_Success() throws Exception {
        mockMvc.perform(put("/members/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"updateduser\", \"password\": \"password\", \"email\": \"updated@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Member updated successfully"));
    }

    @Test
    void testDeleteMember_Success() throws Exception {
        mockMvc.perform(delete("/members/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Member deleted successfully"));
    }

    @Test
    void testListAllMembers_Success() throws Exception {
        mockMvc.perform(get("/members"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetMemberByEmail_Success() throws Exception {
        Member member = new Member();
        member.setId("1");
        member.setUsername("testuser");
        member.setEmail("testuser@example.com");

        when(memberService.findByEmail("testuser@example.com")).thenReturn(member);

        mockMvc.perform(get("/members/email/testuser@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"));
    }
}
