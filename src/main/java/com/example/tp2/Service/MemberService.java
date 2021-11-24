package com.example.tp2.Service;

import com.example.tp2.Repository.Member.MemberRepository;
import com.example.tp2.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(Member member){

        memberRepository.save(member);

        return member.getId();
    }



    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

    public List<Member> getMemberList(){
        return memberRepository.findAll();
    }

}
