package com.part2.comarket.company.query.repository;

import com.part2.comarket.company.query.dto.response.CompanyResponseDTO;

public interface CompanyRepositoryQuerydsl {
    CompanyResponseDTO findCompanyById(Long id);
}
