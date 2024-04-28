package com.part2.comarket.member.command.application;

import com.part2.comarket.member.command.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member registerMember(Member member);

    Optional<Member> findById(Long id);

    Member updateMember(long id, Member updatedInfo);

    void deleteMember(long id);

    Optional<Member> getMemberById(long id);

    List<Member> getAllMembers();
}
