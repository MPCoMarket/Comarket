package com.part2.comarket.company.command.application;

import com.part2.comarket.company.command.domain.Company;
import com.part2.comarket.company.command.dto.request.CompanyPostDTO;
import com.part2.comarket.company.command.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public void addCompany(CompanyPostDTO request) {
        companyRepository.save(request.toEntity());
    }
}
