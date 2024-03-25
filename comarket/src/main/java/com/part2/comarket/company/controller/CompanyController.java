package com.part2.comarket.company.controller;

import com.part2.comarket.company.command.application.SaveCompanyService;
import com.part2.comarket.company.command.dto.request.CompanyPostDTO;
import com.part2.comarket.company.query.application.CompanyService;
import com.part2.comarket.company.query.dto.response.CompanyResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final SaveCompanyService saveCompanyService;
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Void> saveCompany(@RequestBody final CompanyPostDTO request) {
        saveCompanyService.addCompany(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDTO> getCompany(@PathVariable final Long companyId) {
        final CompanyResponseDTO company = companyService.getCompany(companyId);
        return ResponseEntity.ok(company);
    }

}
