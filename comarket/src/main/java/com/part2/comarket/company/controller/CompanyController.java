package com.part2.comarket.company.controller;

import com.part2.comarket.company.command.application.DeleteCompanyService;
import com.part2.comarket.company.command.application.SaveCompanyService;
import com.part2.comarket.company.command.application.UpdateCompanyService;
import com.part2.comarket.company.command.dto.request.CompanyPatchDTO;
import com.part2.comarket.company.command.dto.request.CompanyPostDTO;
import com.part2.comarket.company.query.application.CompanyService;
import com.part2.comarket.company.query.application.SearchCompanyService;
import com.part2.comarket.company.query.dto.response.CompanyResponseDTO;
import com.part2.comarket.company.query.dto.response.SearchCompanyResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final SaveCompanyService saveCompanyService;
    private final UpdateCompanyService updateCompanyService;
    private final DeleteCompanyService deleteCompanyService;
    private final CompanyService companyService;
    private final SearchCompanyService searchCompanyService;

    @PostMapping
    public ResponseEntity<Void> saveCompany(@Valid @RequestBody final CompanyPostDTO request) {
        saveCompanyService.addCompany(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDTO> getCompany(@PathVariable(name = "companyId") final Long companyId) {
        final CompanyResponseDTO company = companyService.getCompany(companyId);
        return ResponseEntity.ok(company);
    }

    @PatchMapping("/{companyId}")
    public ResponseEntity<Void> updateCompany(@PathVariable(name = "companyId") final Long companyId, @Valid @RequestBody final CompanyPatchDTO request) {
        updateCompanyService.updateCompany(companyId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable(name = "companyId") final Long companyId) {
        deleteCompanyService.deleteCompany(companyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchCompanyResponseDTO>> searchCompany(@RequestParam(value = "keyword") final String keyword) {
        final List<SearchCompanyResponseDTO> companies = searchCompanyService.searchCompany(keyword);
        return ResponseEntity.ok(companies);
    }


}
