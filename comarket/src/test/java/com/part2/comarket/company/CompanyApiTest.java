package com.part2.comarket.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.part2.comarket.ApiTest;
import com.part2.comarket.company.command.dto.request.CompanyPostDTO;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

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
    void 회사_등록_실패(){
        //given
        var request = new CompanyPostDTO("", "1234567890", null, "TEST");

        //when
        final var response = CompanySteps.회사_등록_요청(request);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void 회사_조회_성공() throws JsonProcessingException {
        //given
        CompanyPostDTO request = CompanySteps.회사_등록_요청_DTO();
        CompanySteps.회사_등록_요청(request);
        final Long companyId = 1L;

        //when
        final var response = CompanySteps.회사_조회_요청(companyId);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        // 반환값 확인 필요
        ResponseBodyExtractionOptions responseBody = response.body();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(responseBody.asString(), Map.class);
        assertThat(map.get("name")).isEqualTo(request.name()); // 예상되는 값과 비교
        assertThat(map.get("registeredNumber")).isEqualTo(request.registeredNumber());
        assertThat(map.get("location")).isEqualTo(request.location());
        assertThat(map.get("ownerName")).isEqualTo(request.ownerName());
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

    @Test
    void 회사_수정(){
        //given
        CompanyPostDTO request = CompanySteps.회사_등록_요청_DTO();
        CompanySteps.회사_등록_요청(request);
        final Long companyId = 1L;

        //when
        final var response = CompanySteps.회사_수정_요청(companyId, request);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void 회사_수정_실패(){
        //given
        final Long companyId = 0L;

        //when
        final var response = CompanySteps.회사_수정_요청(companyId, CompanySteps.회사_수정_요청_DTO());

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void 회사_삭제(){
        //given
        CompanyPostDTO request = CompanySteps.회사_등록_요청_DTO();
        CompanySteps.회사_등록_요청(request);
        final Long companyId = 1L;

        //when
        final var response = CompanySteps.회사_삭제_요청(companyId);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void 회사_크롤링_조회(){
        //given
        final String keyword = "카카오";

        //when
        final var response = CompanySteps.회사_크롤링_조회_요청(keyword);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
