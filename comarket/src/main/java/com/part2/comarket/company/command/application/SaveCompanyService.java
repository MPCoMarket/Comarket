package com.part2.comarket.company.command.application;

import com.part2.comarket.common.exception.CustomException;
import com.part2.comarket.common.exception.ErrorCode;
import com.part2.comarket.company.command.domain.Company;
import com.part2.comarket.company.command.dto.request.CompanyPostDTO;
import com.part2.comarket.company.command.repository.CompanyRepository;
import com.part2.comarket.company.query.application.SearchCompanyService;
import com.part2.comarket.company.query.dto.response.SearchCompanyResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveCompanyService {

    private final CompanyRepository companyRepository;
    private final SearchCompanyService searchCompanyService;

    public Company addCompany(CompanyPostDTO request) {
        List<SearchCompanyResponseDTO> companyList = searchCompanyService.searchCompany(request.registeredNumber());
        if (companyList.isEmpty()) {
            throw new CustomException(ErrorCode.COMPANY_NOT_FOUND_BY_REGISTERED_NUMBER);
        }

        return companyRepository.save(SearchCompanyResponseDTO.toEntity(companyList.get(0)));
    }
}
