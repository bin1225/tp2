package com.example.tp2.Repository.Member;

import com.example.tp2.domain.Member;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemberRepository {

    Long save(Member member);

    Member findOne(Long id);

    List<Member> findByUserId(String userId);

    List<Member> findAll() ;

    List findByName(String name);
}
