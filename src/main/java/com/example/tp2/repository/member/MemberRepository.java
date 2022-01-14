package com.example.tp2.repository.member;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.tp2.domain.Member;

@Repository
public interface MemberRepository {

	Long save(Member member);

	Member findOne(Long id);

	List<Member> findByUserId(String userId);

	List<Member> findAll();

	List findByName(String name);
}
