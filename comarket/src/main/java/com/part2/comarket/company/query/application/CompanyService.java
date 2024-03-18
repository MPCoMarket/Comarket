package com.part2.comarket.company.query.application;

import com.part2.comarket.company.command.domain.Company;
import com.part2.comarket.company.query.dto.response.CompanyResponseDTO;
import com.part2.comarket.company.query.repository.CompanyRepositoryQuery;
import com.part2.comarket.common.exception.CustomException;
import com.part2.comarket.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    final CompanyRepositoryQuery companyRepository;

    public CompanyResponseDTO getCompany(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.COMPANY_NOT_FOUND)
        );
        return CompanyResponseDTO.fromEntity(company);
    }
}
