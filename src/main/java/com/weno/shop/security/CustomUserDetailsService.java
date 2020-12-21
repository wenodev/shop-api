package com.weno.shop.security;

import com.weno.shop.entity.Member;
import com.weno.shop.entity.MemberRepository;
import com.weno.shop.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private final MemberRepository memberRepository;


    // Member Id로 확인
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("no member id : " + userId));

        return UserPrincipal.create(member);
    }


    // Member pk로 확인
    @Transactional
    public UserDetails loadUserById(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no user  id : " + id ));

        return UserPrincipal.create(member);
    }


}
