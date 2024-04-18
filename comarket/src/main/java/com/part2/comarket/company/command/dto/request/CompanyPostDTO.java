package com.part2.comarket.company.command.dto.request;


import com.part2.comarket.company.command.domain.Company;
import jakarta.validation.constraints.NotBlank;

public record CompanyPostDTO(

        @NotBlank(message = "회사이름은 필수 입니다.")
        String name,
        @NotBlank(message = "사업자등록번호는 필수입니다.")
        String registeredNumber,
        @NotBlank(message = "소재지는 필수입니다.")
        String location,
        @NotBlank(message = "대표자이름은 필수입니다.")
        String ownerName
) {
        public Company toEntity() {
                return new Company(name, registeredNumber, location, ownerName);
        }
}