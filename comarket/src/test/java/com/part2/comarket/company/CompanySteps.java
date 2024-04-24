package com.part2.comarket.company;

import com.part2.comarket.company.command.dto.request.CompanyPostDTO;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class CompanySteps {

    static CompanyPostDTO 회사_등록_요청_DTO() {
        final String name = "주식회사 카카오";
        final String registeredNumber = "1208147521";
        final String location = "제주특별자치도 제주시 첨단로 242,";
        final String ownerName = "홍은택";
        return new CompanyPostDTO(name, registeredNumber, location, ownerName);
    }

    public static ExtractableResponse<Response> 회사_등록_요청(CompanyPostDTO request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/companies")
                .then()
                .log().all().extract();
    }

    public static ExtractableResponse<Response> 회사_조회_요청(Long companyId) {
        return RestAssured.given().log().all()
                .when()
                .get("/companies/{companyId}" , companyId)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 회사_수정_요청(Long companyId, CompanyPostDTO request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .patch("/companies/{companyId}", companyId)
                .then().log().all()
                .extract();
    }

    public static CompanyPostDTO 회사_수정_요청_DTO() {
        final String name = "회사이름";
        final String registeredNumber = "사업자등록번호";
        final String location = "소재지";
        final String ownerName = "대표자이름";
        return new CompanyPostDTO(name, registeredNumber, location, ownerName);
    }

    public static ExtractableResponse<Response> 회사_삭제_요청(Long companyId) {
        return RestAssured.given().log().all()
                .when()
                .delete("/companies/{companyId}", companyId)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 회사_크롤링_조회_요청(String keyword) {
        return RestAssured.given().log().all()
                .when()
                .get("/companies/search?keyword={keyword}", keyword)
                .then()
                .log().all()
                .extract();
    }
}
