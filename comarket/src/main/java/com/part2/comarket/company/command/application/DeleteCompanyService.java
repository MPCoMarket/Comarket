package com.part2.comarket.company.command.application;

import com.part2.comarket.company.command.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public void deleteCompany(Long companyId) {
        companyRepository.deleteById(companyId);
    }
}
