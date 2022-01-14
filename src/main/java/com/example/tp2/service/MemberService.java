package com.example.tp2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tp2.repository.member.MemberRepository;
import com.example.tp2.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;

	public Long join(Member member) {

		memberRepository.save(member);

		return member.getId();
	}

	public Optional<Member> findByUserId(String userId) {
		List<Member> byUserId = memberRepository.findByUserId(userId);
		return byUserId.stream().findFirst();
	}

	public Member findOne(Long id) {
		return memberRepository.findOne(id);
	}

	public List<Member> getMemberList() {
		return memberRepository.findAll();
	}

}
