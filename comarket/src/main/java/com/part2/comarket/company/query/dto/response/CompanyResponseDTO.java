package com.part2.comarket.company.query.dto.response;


import com.part2.comarket.company.command.domain.Company;

public record CompanyResponseDTO(
        Long id,
        String name,
        String registeredNumber,
        String location,
        String ownerName
) {
        public static CompanyResponseDTO fromEntity(Company company) {
                return new CompanyResponseDTO(company.getId(), company.getName(), company.getRegisteredNumber(), company.getLocation(), company.getOwnerName());
        }
}