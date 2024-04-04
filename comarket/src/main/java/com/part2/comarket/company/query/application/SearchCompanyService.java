package com.part2.comarket.company.query.application;

import com.part2.comarket.company.query.dto.response.SearchCompanyResponseDTO;

import java.util.List;

public interface SearchCompanyService {
    List<SearchCompanyResponseDTO> searchCompany(String keyword);
}
