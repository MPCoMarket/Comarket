package com.part2.comarket.member.command.application;

import com.part2.comarket.member.command.domain.Member;

import java.util.Optional;

public interface MemberService {
    Member register(Member member);

    Optional<Member> findById(Long id);
}
