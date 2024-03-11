package com.part2.comarket.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberService memberService;

    @Test
    void 회원가입_테스트() {
        // given
        Member newMember = new Member("test@exemple.com", "password", "TestUser");

        // when
        Member savedMember = memberService.register(newMember);
        Member foundMember = memberService.findById(savedMember.getId())
                .orElseThrow(() -> new NoSuchElementException("해당 ID의 회원을 찾을 수 없습니다."));

        // then
        assertThat(foundMember.getEmail()).isEqualTo(newMember.getEmail());
    }

    private class MemberService {
        private final MemberRepository memberRepository;

        private MemberService(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        public Member register(Member member) {
            return memberRepository.save(member);
        }

        public Optional<Member> findById(Long memberId) {
            return memberRepository.findById(memberId);
        }
    }

    private class Member {

        private Long id = 1L;
        private final String email;
        private final String password;
        private final String user;

        public Member(String email, String password, String user) {
            this.email = email;
            this.password = password;
            this.user = user;
        }


        public Long getId() {
            return this.id;
        }

        public String getEmail() {
            return this.email;
        }
    }

    interface MemberRepository extends JpaRepository<Member, Long> {
    }
}
