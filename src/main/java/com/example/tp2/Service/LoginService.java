package com.example.tp2.Service;


import com.example.tp2.Repository.Member.MemberRepository;
import com.example.tp2.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    public boolean login(String id, String password){

        List<Member> byUserId = memberRepository.findByUserId(id);
        if (byUserId.size() == 0) {
            return false;
        }
        Member member = byUserId.get(0);
        log.info(member.getUserId());
        log.info(member.getPassword());
        log.info(password);
        if(!member.getPassword().equals(password)){
            log.info("비밀번호가 다릅니다.");
            return false;
        }

        return true;
    }
}
