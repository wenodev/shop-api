package com.weno.shop.member;


import com.weno.shop.member.Member;
import com.weno.shop.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean isExistsByUserId(String userId){
        return memberRepository.existsByUserId(userId);
    }


    @Transactional
    public Member registerMember(Member resource){
        return memberRepository.save(resource);
    }




}
