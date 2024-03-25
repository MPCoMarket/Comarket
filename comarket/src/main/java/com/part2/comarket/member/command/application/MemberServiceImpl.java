package com.part2.comarket.member.command.application;

import com.part2.comarket.member.command.domain.Member;
import com.part2.comarket.member.query.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

//    public MemberServiceImpl(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    public Member register(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
