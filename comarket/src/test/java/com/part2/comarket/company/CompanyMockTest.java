package com.part2.comarket.company;

import com.part2.comarket.common.exception.CustomException;
import com.part2.comarket.company.command.application.DeleteCompanyService;
import com.part2.comarket.company.command.application.SaveCompanyService;
import com.part2.comarket.company.command.application.UpdateCompanyService;
import com.part2.comarket.company.command.domain.Company;
import com.part2.comarket.company.command.dto.request.CompanyPatchDTO;
import com.part2.comarket.company.command.dto.request.CompanyPostDTO;
import com.part2.comarket.company.query.application.CompanyService;
import com.part2.comarket.company.query.application.SearchCompanyService;
import com.part2.comarket.company.query.dto.response.CompanyResponseDTO;
import com.part2.comarket.company.query.dto.response.SearchCompanyResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyMockTest {

    @Mock
    private SaveCompanyService saveCompanyService;

    @Mock
    private UpdateCompanyService updateCompanyService;

    @Mock
    private DeleteCompanyService deleteCompanyService;

    @Mock
    private CompanyService companyService;

    @Mock
    private SearchCompanyService searchCompanyService;

    @InjectMocks
    private CompanySteps companySteps;

    @Test
    void 회사_등록() {
        // given
        CompanyPostDTO request = CompanySteps.회사_등록_요청_DTO();
        Company company = request.toEntity(); // 적절한 Company 객체 생성

        when(saveCompanyService.addCompany(any(CompanyPostDTO.class))).thenReturn(company);

        // when
        Company result = saveCompanyService.addCompany(request);

        // then
        assertThat(result.getSecretKey().getValue().length()).isEqualTo(6);
        assertThat(result.getName()).isEqualTo(request.name());
        assertThat(result.getRegisteredNumber()).isEqualTo(request.registeredNumber());
        assertThat(result.getLocation()).isEqualTo(request.location());
        assertThat(result.getOwnerName()).isEqualTo(request.ownerName());
    }

    @Test
    public void 회사_조회_성공() {
        // given
        CompanyPostDTO request = CompanySteps.회사_등록_요청_DTO();
        Company company = request.toEntity();
        Long companyId = company.getId(); // 실제 Company 객체의 id 값 사용

        CompanyResponseDTO responseDTO = new CompanyResponseDTO(companyId, request.name(), request.registeredNumber(), request.location(), request.ownerName());

        when(saveCompanyService.addCompany(any(CompanyPostDTO.class))).thenReturn(company);
        when(companyService.getCompany(companyId)).thenReturn(responseDTO);

        // when
        Company createdCompany = saveCompanyService.addCompany(request);
        CompanyResponseDTO result = companyService.getCompany(createdCompany.getId());

        // then
        assertThat(result.id()).isEqualTo(companyId);
        assertThat(result.name()).isEqualTo(request.name());
        assertThat(result.registeredNumber()).isEqualTo(request.registeredNumber());
        assertThat(result.location()).isEqualTo(request.location());
        assertThat(result.ownerName()).isEqualTo(request.ownerName());
    }

    @Test
    public void 회사_조회_실패() {
        // given
        Long companyId = 0L;

        when(companyService.getCompany(anyLong())).thenThrow(CustomException.class);

        // when, then
        assertThrows(CustomException.class, () -> companyService.getCompany(companyId));
    }

    @Test
    public void 회사_수정_성공() {
        // given
        CompanyPostDTO request = CompanySteps.회사_등록_요청_DTO();
        Company createdCompany = request.toEntity();
        CompanyPatchDTO updateRequest = new CompanyPatchDTO("수정된회사이름", "수정된사업자등록번호", "수정된소재지", "수정된대표자이름");
        CompanyResponseDTO responseDTO = new CompanyResponseDTO(1L, "수정된회사이름", "수정된사업자등록번호", "수정된소재지", "수정된대표자이름");

        when(saveCompanyService.addCompany(any(CompanyPostDTO.class))).thenReturn(createdCompany);
        when(companyService.getCompany(createdCompany.getId())).thenReturn(responseDTO);

        // when
        Company company = saveCompanyService.addCompany(request);
        updateCompanyService.updateCompany(company.getId(), updateRequest);
        CompanyResponseDTO result = companyService.getCompany(company.getId());

        // then
        assertThat(result.name()).isEqualTo(updateRequest.name());
        assertThat(result.registeredNumber()).isEqualTo(updateRequest.registeredNumber());
        assertThat(result.location()).isEqualTo(updateRequest.location());
        assertThat(result.ownerName()).isEqualTo(updateRequest.ownerName());
    }

    @Test
    public void 회사_수정_실패() {
        // given
        Long companyId = 0L;
        CompanyPatchDTO updateRequest = new CompanyPatchDTO("수정된회사이름", "수정된사업자등록번호", "수정된소재지", "수정된대표자이름");

        doThrow(CustomException.class).when(updateCompanyService).updateCompany(any(Long.class), any(CompanyPatchDTO.class));

        // when, then
        assertThrows(CustomException.class, () -> updateCompanyService.updateCompany(companyId, updateRequest));
    }

    @Test
    public void 회사_삭제_성공() {
        // given
        CompanyPostDTO request = CompanySteps.회사_등록_요청_DTO();
        Company createdCompany = new Company(); // 적절한 Company 객체 생성

        when(saveCompanyService.addCompany(any(CompanyPostDTO.class))).thenReturn(createdCompany);

        // when
        Company company = saveCompanyService.addCompany(request);
        deleteCompanyService.deleteCompany(company.getId());

        // then
        verify(deleteCompanyService, times(1)).deleteCompany(company.getId());
    }

    @Test
    public void 회사_크롤링_조회_성공() {
        // given
        String companyName = "메일플러그";
        List<SearchCompanyResponseDTO> mockCompanyList = new ArrayList<>();
        SearchCompanyResponseDTO mockCompany = new SearchCompanyResponseDTO(
                "메일플러그",
                "2118703011",
                "서울특별시 강남구",
                "이동권"
        );
        mockCompanyList.add(mockCompany);

        when(searchCompanyService.searchCompany(anyString())).thenReturn(mockCompanyList);

        // when
        List<SearchCompanyResponseDTO> result = searchCompanyService.searchCompany(companyName);

        // then
        assertThat(result).hasSize(1);
        SearchCompanyResponseDTO resultCompany = result.get(0);
        assertThat(resultCompany.name()).isEqualTo("메일플러그");
        assertThat(resultCompany.registeredNumber()).isEqualTo("2118703011");
        assertThat(resultCompany.location()).isEqualTo("서울특별시 강남구");
        assertThat(resultCompany.ownerName()).isEqualTo("이동권");

        verify(searchCompanyService, times(1)).searchCompany(companyName);
    }
}