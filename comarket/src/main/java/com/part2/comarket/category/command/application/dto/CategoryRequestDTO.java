package com.part2.comarket.category.command.application.dto;

import com.part2.comarket.category.command.domain.Category;
import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank(message = "카테고리 이름은 필수입니다.")
        String name,
        Long parentId
){
        public Category toEntity(Category parentCategory) {
                return new Category(name, parentCategory);
        }
}