package com.part2.comarket.company;

import com.part2.comarket.company.command.application.CompanyService;
import com.part2.comarket.company.command.domain.Company;
import com.part2.comarket.company.command.dto.request.CompanyPostDTO;
import com.part2.comarket.company.command.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CompanyRegisterTest {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;
    @Test
    void 회사_등록() {
        final CompanyPostDTO request = 회사_등록_요청();
        companyService.addCompany(request);

        final Company company = companyRepository.findAll().get(0);

        System.out.print("날짜:"+company.getCreatedDate());
        System.out.print("key:"+company.getSecretKey().getValue());

        assertThat(company.getRegisteredNumber()).isEqualTo(request.registeredNumber());
        assertThat(company.getLocation()).isEqualTo(request.location());
        assertThat(company.getOwnerName()).isEqualTo(request.ownerName());
    }

    private static CompanyPostDTO 회사_등록_요청() {
        final String registeredNumber = "사업자등록번호";
        final String location = "소재지";
        final String ownerName = "대표자이름";
        return new CompanyPostDTO(registeredNumber, location, ownerName);
    }

}
