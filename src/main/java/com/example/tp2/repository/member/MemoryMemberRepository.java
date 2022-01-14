package com.example.tp2.repository.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.tp2.domain.Member;

public class MemoryMemberRepository implements MemberRepository {

	private static Map<String, Member> store = new HashMap<>();
	private Long id = 0L;

	@Override
	public Long save(Member member) {

		store.put(member.getUserId(), member);
		return member.getId();
	}

	@Override
	public Member findOne(Long id) {
		Member member = store.get(id);
		return member;
	}

	@Override
	public List findByUserId(String userId) {
		return null;
	}

	@Override
	public List<Member> findAll() {
		List<Member> members = new ArrayList<>();
		Iterator<String> keys = store.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			members.add(store.get(key));
		}
		return members;
	}

	@Override
	public List findByName(String name) {
		return null;
	}
}
