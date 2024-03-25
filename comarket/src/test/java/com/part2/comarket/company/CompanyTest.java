package com.part2.comarket.company;

import com.part2.comarket.company.command.application.DeleteCompanyService;
import com.part2.comarket.company.command.application.SaveCompanyService;
import com.part2.comarket.company.command.application.UpdateCompanyService;
import com.part2.comarket.company.command.domain.Company;
import com.part2.comarket.company.command.dto.request.CompanyPatchDTO;
import com.part2.comarket.company.command.dto.request.CompanyPostDTO;
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
    private UpdateCompanyService updateCompanyService;

    @Autowired
    private DeleteCompanyService deleteCompanyService;

    @Autowired
    private CompanyService companyService;

    @Test
    void 회사_등록() {
        final CompanyPostDTO request = CompanySteps.회사_등록_요청_DTO();
        Company company = saveCompanyService.addCompany(request);

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
        Company createdCompany = saveCompanyService.addCompany(request);
        final Long companyId = createdCompany.getId();

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

    @Test
    public void 회사_수정_성공() {
        //given
        final CompanyPostDTO request = CompanySteps.회사_등록_요청_DTO();
        Company createdCompany = saveCompanyService.addCompany(request);
        final Long companyId = createdCompany.getId();
        final CompanyPatchDTO updateRequest = new CompanyPatchDTO("수정된회사이름", "수정된사업자등록번호", "수정된소재지", "수정된대표자이름");

        //when
        updateCompanyService.updateCompany(companyId, updateRequest);
        CompanyResponseDTO company = companyService.getCompany(companyId);

        //then
        assertThat(company.id()).isEqualTo(companyId);
        assertThat(company.name()).isEqualTo(updateRequest.name());
        assertThat(company.registeredNumber()).isEqualTo(updateRequest.registeredNumber());
        assertThat(company.location()).isEqualTo(updateRequest.location());
        assertThat(company.ownerName()).isEqualTo(updateRequest.ownerName());
    }

    @Test
    public void 회사_수정_실패() {
        //given
        final Long companyId = 0L;
        final CompanyPatchDTO updateRequest = new CompanyPatchDTO("수정된회사이름", "수정된사업자등록번호", "수정된소재지", "수정된대표자이름");

        //when
        //then
        assertThrows(CustomException.class, () -> updateCompanyService.updateCompany(companyId, updateRequest));
    }

    @Test
    public void 회사_삭제_성공() {
        //given
        final CompanyPostDTO request = CompanySteps.회사_등록_요청_DTO();
        Company createdCompany = saveCompanyService.addCompany(request);
        final Long companyId = createdCompany.getId();

        //when
        deleteCompanyService.deleteCompany(companyId);

        //then
        assertThrows(CustomException.class, () -> companyService.getCompany(companyId));
    }

}
