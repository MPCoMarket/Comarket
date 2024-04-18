package com.part2.comarket.company.command.application;

import com.part2.comarket.company.command.domain.Company;
import com.part2.comarket.company.command.dto.request.CompanyPostDTO;
import com.part2.comarket.company.command.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveCompanyService {

    private final CompanyRepository companyRepository;

    public Company addCompany(CompanyPostDTO request) {
        return companyRepository.save(request.toEntity());
    }
}
