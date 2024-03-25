package com.part2.comarket.company.command.dto.request;


import com.part2.comarket.company.command.domain.Company;
import jakarta.validation.constraints.NotBlank;

public record CompanyPatchDTO(
        String name,
        String registeredNumber,
        String location,
        String ownerName
) {
        public Company toEntity() {
                return new Company(name, registeredNumber, location, ownerName);
        }
}