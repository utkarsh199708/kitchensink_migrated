package com.mongodb.kitchensink_migrated.controller;

import com.mongodb.kitchensink_migrated.security.JwtTokenUtil;
import com.mongodb.kitchensink_migrated.service.JwtUserDetailsService;
import com.mongodb.kitchensink_migrated.model.JwtRequest;
import com.mongodb.kitchensink_migrated.model.JwtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        logger.info("Authentication request received for username: {}", authenticationRequest.getUsername());

        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);

            logger.info("Authentication successful for username: {}", authenticationRequest.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            logger.error("Authentication failed for username: {}", authenticationRequest.getUsername(), e);
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            logger.warn("User disabled: {}", username);
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            logger.warn("Invalid credentials for username: {}", username);
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
