package com.example.tp2.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.tp2.repository.member.MemberRepository;
import com.example.tp2.domain.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LoginService {

	private final MemberRepository memberRepository;

	public boolean login(String id, String password) {

		List<Member> byUserId = memberRepository.findByUserId(id);
		if (byUserId.size() == 0) {
			return false;
		}
		Member member = byUserId.get(0);
		if (!member.getPassword().equals(password)) {
			log.info("비밀번호가 다릅니다.");
			return false;
		}

		return true;
	}
}
