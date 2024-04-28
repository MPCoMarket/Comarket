package com.part2.comarket.member;

import com.part2.comarket.company.command.domain.Company;
import com.part2.comarket.member.command.application.MemberService;
import com.part2.comarket.member.command.domain.Member;
import com.part2.comarket.member.query.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class MemberServiceTest {

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

//    @Test
//    void createMemberTest() {
//        Member member = new Member();
//        Company company = new Company();
//        String secretKey = "KRIkVUsRgiCov+6evBHHjtLsvgktOTqOBVXK02dZVXk=";
//        member.setCompany(company);
//        member.setUsername("newMember");
//        member.setEmail("new@example.com");
//
//        when(memberRepository.save(any(Member.class))).thenReturn(member);
//        when(companyRepository.findBySecretKey(secretKey)).thenReturn(Optional.of(company));
//
//        Member created = memberService.createMember(member);
//
//        assertNotNull(created);
//        assertEquals("newMember", created.getUserName());
//    }

    @Test
    void updateMemberTest() {
        Member member = new Member(1L, "originalMember", "original@email.com", "010-0000-0000", "0000");

        // JAP 변경감지 이용한 테스트 코드
        member.setUserName("updatedMember");
        member.setEmail("updated@example.com");

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
//        when(memberRepository.save(any(Member.class))).thenAnswer(i -> (Member) i.getArguments()[0]);
        when(memberRepository.save(ArgumentMatchers.isA(Member.class))).thenAnswer(i -> i.getArguments()[0]);

        Member updated = memberService.updateMember(1L, member);

        assertNotNull(updated);
        assertEquals("updatedMember", updated.getUserName());
        assertEquals("updated@example.com", updated.getEmail());
    }

    @Test
    void deleteMemberTest() {
        doNothing().when(memberRepository).deleteById(1L);

        memberService.deleteMember(1L);

        verify(memberRepository, times(1)).deleteById(1L);
    }

    @Test
    void getMemberByIdTest() {
        Member member = new Member();
        member.setId(1L);
        member.setUserName("testMember");

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        Optional<Member> found = memberService.getMemberById(1L);

        assertNotNull(found);
        assertEquals("testMember", found);
    }

    @Test
    void getAllMembersTest() {
        Member member1 = new Member();
        member1.setUserName("testMember1");
        Member member2 = new Member();
        member2.setUserName("testMember2");

        when(memberRepository.findAll()).thenReturn(Arrays.asList(member1, member2));

        List<Member> members = memberService.getAllMembers();

        assertNotNull(members);
        assertEquals(2, members.size());
        assertEquals("testMember1", members.get(0).getUserName());
    }

    private interface CompanyRepository {
        Optional<Company> findBySecretKey(String secretKey);
    }
}
