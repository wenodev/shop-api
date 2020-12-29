package com.weno.shop.controller;

import com.weno.shop.entity.Member;
import com.weno.shop.entity.Role;
import com.weno.shop.entity.RoleName;
import com.weno.shop.entity.RoleRepository;
import com.weno.shop.exception.ResourceNotFoundException;
import com.weno.shop.security.jwt.JwtTokenProvider;
import com.weno.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {


    private final MemberService memberService;

//    private final RoleRepository roleRepository;
//
//    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenProvider tokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;


    @PostMapping("/signin")
    public ResponseEntity loginUser(@RequestBody Member resource){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(resource.getUserId(), resource.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return new ResponseEntity(jwt, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity registerMember(@RequestBody Member resource ){

        if(memberService.isExistsByUserId(resource.getUserId())){
            return new ResponseEntity("userId is already taken", HttpStatus.BAD_REQUEST);
        }

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new ResourceNotFoundException("User Role not set."));

        Member member = Member.builder()
                .userId(resource.getUserId())
                .password(passwordEncoder.encode(resource.getPassword()))
                .name(resource.getName())
                .roles(Collections.singleton(userRole))
                .build();

        memberService.registerMember(member);

        return new ResponseEntity(member, HttpStatus.CREATED);
    }
    
}
