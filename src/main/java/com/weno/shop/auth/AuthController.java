package com.weno.shop.auth;

import com.weno.shop.exception.ResourceNotFoundException;
import com.weno.shop.member.Member;
import com.weno.shop.member.MemberService;
import com.weno.shop.role.Role;
import com.weno.shop.role.RoleName;
import com.weno.shop.role.RoleRepository;
import com.weno.shop.security.CurrentUser;
import com.weno.shop.security.UserPrincipal;
import com.weno.shop.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
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
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/signin")
    public ResponseEntity loginUser(@RequestBody Member resource){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(resource.getUserId(), resource.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return new ResponseEntity(new JwtAuthenticationResponse(jwt), HttpStatus.OK);
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

    @GetMapping("/current")
    public Member getCurrentMember(@CurrentUser UserPrincipal userPrincipal){

        Member member = Member.builder()
                .userId(userPrincipal.getUserId())
                .build();

        return member;
    }
    
}
