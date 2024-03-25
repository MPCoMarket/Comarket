package com.part2.comarket.company.query.repository;

import com.part2.comarket.company.command.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepositoryQuery extends JpaRepository<Company, Long> {
}
