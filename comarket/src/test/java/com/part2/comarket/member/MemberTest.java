package com.part2.comarket.member;

import com.part2.comarket.member.command.application.MemberService;
import com.part2.comarket.member.command.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

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
        Member member = memberService.register(newMember);
        Member foundMember = memberService.findById(member.getId())
                .orElseThrow(() -> new NoSuchElementException("해당 ID의 회원을 찾을 수 없습니다."));

        // then
        assertThat(foundMember.getEmail()).isEqualTo(newMember.getEmail());
    }
}
