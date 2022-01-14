package com.example.tp2.repository.member;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.tp2.domain.Member;

@Repository
public class H2MemberRepositroy implements MemberRepository {

	@PersistenceContext
	private EntityManager em;

	public Long save(Member member) {
		em.persist(member);
		return member.getId();
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	public List findByUserId(String userId) {
		return em.createQuery("select m from Member m where m.userId=:userId")
			.setParameter("userId", userId)
			.getResultList();

	}

	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
			.getResultList();
	}

	public List findByName(String name) {
		return em.createQuery("select m from Member m where m.name =:name")
			.setParameter("name", name)
			.getResultList();
	}
}
