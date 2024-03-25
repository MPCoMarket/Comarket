package com.part2.comarket.company;

import com.part2.comarket.ApiTest;
import com.part2.comarket.company.command.dto.request.CompanyPostDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CompanyApiTest extends ApiTest {

    @Test
    void 회사_등록() {
        //given
        final var request = CompanySteps.회사_등록_요청_DTO();

        //when
        final var response = CompanySteps.회사_등록_요청(request);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void 회사_조회_성공(){
        //given
        CompanyPostDTO request = CompanySteps.회사_등록_요청_DTO();
        CompanySteps.회사_등록_요청(request);
        final Long companyId = 1L;

        //when
        final var response = CompanySteps.회사_조회_요청(companyId);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void 회사_조회_실패(){
        //given
        final Long companyId = 0L;

        //when
        final var response = CompanySteps.회사_조회_요청(companyId);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
