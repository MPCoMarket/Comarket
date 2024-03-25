package com.part2.comarket.member;

import java.util.Optional;

class MemberService {
    private final MemberRepository memberRepository;

    MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long register(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
