package com.part2.comarket.company;

import com.part2.comarket.company.command.dto.request.CompanyPostDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CompanyPostDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        // Validator 초기화
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void 회사이름_필수() {
        // given
        CompanyPostDTO request = new CompanyPostDTO("", "1234567890", "서울시 강남구", "홍길동");

        // when
        Set<ConstraintViolation<CompanyPostDTO>> violations = validator.validate(request);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<CompanyPostDTO> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("회사이름은 필수 입니다.");
    }

    @Test
    void 사업자등록번호_필수() {
        // given
        CompanyPostDTO request = new CompanyPostDTO("회사이름", "", "서울시 강남구", "홍길동");

        // when
        Set<ConstraintViolation<CompanyPostDTO>> violations = validator.validate(request);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<CompanyPostDTO> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("사업자등록번호는 필수입니다.");
    }

    @Test
    void 소재지_필수() {
        // given
        CompanyPostDTO request = new CompanyPostDTO("회사이름", "1234567890", "", "홍길동");

        // when
        Set<ConstraintViolation<CompanyPostDTO>> violations = validator.validate(request);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<CompanyPostDTO> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("소재지는 필수입니다.");
    }

    @Test
    void 대표자이름_필수() {
        // given
        CompanyPostDTO request = new CompanyPostDTO("회사이름", "1234567890", "서울시 강남구", "");

        // when
        Set<ConstraintViolation<CompanyPostDTO>> violations = validator.validate(request);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<CompanyPostDTO> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("대표자이름은 필수입니다.");
    }

    @Test
    void 모든_필드_유효() {
        // given
        CompanyPostDTO request = new CompanyPostDTO("회사이름", "1234567890", "서울시 강남구", "홍길동");

        // when
        Set<ConstraintViolation<CompanyPostDTO>> violations = validator.validate(request);

        // then
        assertThat(violations).isEmpty();
    }

    @Test
    void 모든_필드_비었을_때() {
        // given
        CompanyPostDTO request = new CompanyPostDTO("", "", "", "");

        // when
        Set<ConstraintViolation<CompanyPostDTO>> violations = validator.validate(request);

        // then
        assertThat(violations).hasSize(4);
        for (ConstraintViolation<CompanyPostDTO> violation : violations) {
            if (violation.getMessage().equals("회사이름은 필수 입니다.")) {
                // 회사이름 필수 조건 위반 확인
            } else if (violation.getMessage().equals("사업자등록번호는 필수입니다.")) {
                // 사업자등록번호 필수 조건 위반 확인
            } else if (violation.getMessage().equals("소재지는 필수입니다.")) {
                // 소재지 필수 조건 위반 확인
            } else if (violation.getMessage().equals("대표자이름은 필수입니다.")) {
                // 대표자이름 필수 조건 위반 확인
            }
        }
    }
}