package com.part2.comarket.member;

import com.part2.comarket.ApiTest;
import com.part2.comarket.company.command.domain.Company;
import com.part2.comarket.member.command.application.MemberService;
import com.part2.comarket.member.command.domain.Member;
import com.part2.comarket.member.controller.request.CreateMemberRequest;
import com.part2.comarket.member.query.repository.MemberRepository;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class MemberApiTest extends ApiTest {

    @MockBean
    private CompanyRepository companyRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    void 회원가입_테스트() {
        // given
        String secretKey = "KRIkVUsRgiCov+6evBHHjtLsvgktOTqOBVXK02dZVXk=";
        Company company = new Company();
        company.setSecretKey(secretKey);

        Member newMember = new Member("test@exemple.com", "000-0000-0000", "password", "TestUser");
        newMember.setCompany(company);

        when(companyRepository.findBySecretKey(secretKey)).thenReturn(Optional.of(company));
//        when(memberRepository.save(Mockito.any(Member.class))).thenReturn(newMember);

        // when
        Member registeredMember = memberService.registerMember(newMember);
        Member foundMember = memberService.findById(registeredMember.getId())
                .orElseThrow(() -> new NoSuchElementException("해당 ID의 회원을 찾을 수 없습니다."));

        // then
        assertNotNull(registeredMember);
        assertThat(foundMember.getEmail()).isEqualTo(newMember.getEmail());
        assertThat(company).isEqualTo(registeredMember.getCompany());
    }

    private interface CompanyRepository {

        Optional<Company> findBySecretKey(String secretKey);
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
