package com.part2.comarket.member;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberTest {

    private MemberService memberService = new MemberService(new MemberRepositoryImpl());

    @Test
    void 회원가입_테스트() {
        // given
        Member newMember = new Member("test@exemple.com", "password", "TestUser");

        // when
        Long memberId = memberService.register(newMember);
        Member foundMember = memberService.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("해당 ID의 회원을 찾을 수 없습니다."));

        // then
        assertThat(foundMember.getEmail()).isEqualTo(newMember.getEmail());
    }

}
