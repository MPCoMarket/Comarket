package com.part2.comarket.category.query.dto;

import com.part2.comarket.category.command.domain.Category;

import java.util.Objects;

public record CategoryResponseDTO (
    Long id,
    String name,
    Long parentId
){
    public CategoryResponseDTO(Category category) {
        this(Objects.requireNonNull(category.getId()),
                category.getName(),
                category.getParentId().getId());
    }
    public static CategoryResponseDTO fromEntity(Category category) {
        return new CategoryResponseDTO(category);
    }
}