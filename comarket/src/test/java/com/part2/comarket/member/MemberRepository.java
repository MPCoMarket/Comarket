package com.part2.comarket.member;

import java.util.Optional;

interface MemberRepository {

    Optional<Member> findById(Long id);

    Long save(Member member);
}
