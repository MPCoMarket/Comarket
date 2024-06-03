package com.part2.comarket.member.command.application;

import com.part2.comarket.member.command.domain.Member;
import com.part2.comarket.member.query.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Register a new member with validation.
     *
     * @param member Member to be registered
     * @return the saved Member
     */
    public Member registerMember(Member member) {
        validateMember(member);
        return memberRepository.save(member);
    }

    /**
     * Find a member by ID
     *
     * @param memberId ID of the member to be found.
     * @return The saved Member.
     */
    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /**
     * Update an existing member with validation.
     *
     * @param memberId ID of the member to be updated.
     * @param updatedInfo Updated information for the member.
     * @return The updated Member.
     */
    public Member updateMember(long memberId, Member updatedInfo) {
        validateMember(updatedInfo);
        return memberRepository.save(updatedInfo);
    }

    /**
     * Delete a member by ID.
     *
     * @param id ID of the member to be deleted.
     */
    @Override
    public void deleteMember(long id) {
        memberRepository.deleteById(id);
    }

    /**
     * Get a member by ID.
     *
     * @param id ID of the member to be found.
     * @return Optional containing the found Member, or empty if not found.
     */
    @Override
    public Optional<Member> getMemberById(long id) {
        return memberRepository.findById(id);
    }

    /**
     * Get all members.
     *
     * @return List of all members.
     */
    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    /**
     * Validate the member object to ensure it meets the required criteria.
     *
     * @param member Member to be validated.
     */
    private void validateMember(Member member) {
        if (member.getUserName() == null || member.getUserName().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be empty");
        }
        // 추가 검증로직 필요 시 추가
    }
}
