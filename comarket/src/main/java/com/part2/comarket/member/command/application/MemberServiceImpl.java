package com.part2.comarket.member.command.application;

import com.part2.comarket.member.command.domain.Member;
import com.part2.comarket.member.query.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Member updateMember(long memberId, Member updatedInfo) {
        return memberRepository.save(updatedInfo);
    }

    @Override
    public void deleteMember(long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public Optional<Member> getMemberById(long id) {
        return memberRepository.findById(id);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
