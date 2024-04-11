package com.part2.comarket.company.query.dto.response;


import com.part2.comarket.company.command.domain.Company;

public record SearchCompanyResponseDTO(
        String name,
        String registeredNumber,
        String location,
        String ownerName
) {
    public static SearchCompanyResponseDTO fromEntity(Company company) {
        return new SearchCompanyResponseDTO(company.getName(), company.getRegisteredNumber(), company.getLocation(), company.getOwnerName());
    }

    public static Company toEntity(SearchCompanyResponseDTO dto) {
        return new Company(dto.name(), dto.registeredNumber(), dto.location(), dto.ownerName());
    }
}