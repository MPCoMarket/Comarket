package com.part2.comarket.category.command.dto;

import com.part2.comarket.category.command.domain.Category;
import jakarta.validation.constraints.NotBlank;

import javax.annotation.Nullable;

public record CategoryPostRequestDTO(
        @NotBlank(message = "카테고리 이름은 필수입니다.")
        String name,
        Long parentId
) {

    public Category toEntity(@Nullable Category parentCategory) {
        return new Category(name, parentCategory);
    }
}