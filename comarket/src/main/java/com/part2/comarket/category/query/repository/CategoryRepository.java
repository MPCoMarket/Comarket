package com.part2.comarket.category.query.repository;

import com.part2.comarket.category.command.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}