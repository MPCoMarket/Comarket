package com.part2.comarket.company.command.application;

import com.part2.comarket.common.exception.CustomException;
import com.part2.comarket.common.exception.ErrorCode;
import com.part2.comarket.company.command.dto.request.CompanyPatchDTO;
import com.part2.comarket.company.command.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public void updateCompany(Long companyId, CompanyPatchDTO request) {
        companyRepository.findById(companyId).orElseThrow(
                () -> new CustomException(ErrorCode.COMPANY_NOT_FOUND)
        ).update(request);
    }

}
