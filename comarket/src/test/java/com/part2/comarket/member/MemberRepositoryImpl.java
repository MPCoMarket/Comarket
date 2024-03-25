package com.part2.comarket.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository {

    private Map<Long, Member> persistence = new HashMap<>();

    private Long sequence = 0L;


    @Override
    public Long save(Member member) {
        member.assignId(++sequence);
        persistence.put(member.getId(), member);

        return member.getId();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(persistence.get(id));
    }
}
