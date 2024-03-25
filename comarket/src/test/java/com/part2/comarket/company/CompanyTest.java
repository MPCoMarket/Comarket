package com.part2.comarket.company;

import com.part2.comarket.company.command.application.SaveCompanyService;
import com.part2.comarket.company.command.domain.Company;
import com.part2.comarket.company.command.dto.request.CompanyPostDTO;
import com.part2.comarket.company.command.repository.CompanyRepository;
import com.part2.comarket.company.query.application.CompanyService;
import com.part2.comarket.company.query.dto.response.CompanyResponseDTO;
import com.part2.comarket.common.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CompanyTest {

    @Autowired
    private SaveCompanyService saveCompanyService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void 회사_등록() {
        final CompanyPostDTO request = CompanySteps.회사_등록_요청_DTO();
        saveCompanyService.addCompany(request);

        final Company company = companyRepository.findAll().get(0);

        System.out.print("날짜:"+company.getCreatedDate());

        assertThat(company.getSecretKey().getValue().length()).isEqualTo(6);
        assertThat(company.getName()).isEqualTo(request.name());
        assertThat(company.getRegisteredNumber()).isEqualTo(request.registeredNumber());
        assertThat(company.getLocation()).isEqualTo(request.location());
        assertThat(company.getOwnerName()).isEqualTo(request.ownerName());
    }

    @Test
    public void 회사_조회_성공(){
        //given
        final CompanyPostDTO request = CompanySteps.회사_등록_요청_DTO();
        saveCompanyService.addCompany(request);
        final Long companyId = 1L;

        //when
        final CompanyResponseDTO company = companyService.getCompany(companyId);

        //then
        assertThat(company.id()).isEqualTo(companyId);
        assertThat(company.name()).isEqualTo(request.name());
        assertThat(company.registeredNumber()).isEqualTo(request.registeredNumber());
        assertThat(company.location()).isEqualTo(request.location());
        assertThat(company.ownerName()).isEqualTo(request.ownerName());
    }

    @Test
    public void 회사_조회_실패(){
        //given
        final Long companyId = 0L;

        //when
        //then
        assertThrows(CustomException.class, () -> companyService.getCompany(companyId));
    }

}
