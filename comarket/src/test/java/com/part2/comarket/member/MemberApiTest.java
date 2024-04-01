package com.part2.comarket.member;

import com.part2.comarket.ApiTest;
import com.part2.comarket.member.command.application.MemberService;
import com.part2.comarket.member.command.application.MemberServiceImpl;
import com.part2.comarket.member.command.domain.Member;
import com.part2.comarket.member.controller.request.CreateMemberRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberApiTest extends ApiTest {

    @Autowired
    private MemberService memberService;

    @Test
    void 회원가입_테스트() {
        // given
        Member newMember = new Member("test@exemple.com", "000-0000-0000", "password", "TestUser");

        // when
        Member member = memberService.register(newMember);
        Member foundMember = memberService.findById(member.getId())
                .orElseThrow(() -> new NoSuchElementException("해당 ID의 회원을 찾을 수 없습니다."));

        // then
        assertThat(foundMember.getEmail()).isEqualTo(newMember.getEmail());
    }

    @Test
    void 회원가입() {

        CreateMemberRequest request = 회원가입요청_생성();

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/members")
                .then()
                .log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private CreateMemberRequest 회원가입요청_생성() {
        final String email = "yooil405@naver.com";
        final String phoneNumber = "000-0000-0000";
        final String password = "0000";
        final String userName = "정유일";
        final String secretKey = "KRIkVUsRgiCov+6evBHHjtLsvgktOTqOBVXK02dZVXk=";
        return new CreateMemberRequest(email, phoneNumber, password, userName, secretKey);
    }

}
