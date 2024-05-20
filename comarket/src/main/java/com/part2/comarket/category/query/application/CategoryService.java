package com.part2.comarket.category.query.application;

import com.part2.comarket.category.command.domain.Category;
import com.part2.comarket.category.query.dto.CategoryResponseDTO;
import com.part2.comarket.category.query.repository.CategoryRepository;
import com.part2.comarket.common.exception.CustomException;
import com.part2.comarket.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // 단일 카테고리 조회
    @Transactional(readOnly = true)                 // 엔티티가 변경되지 못하도록 한다.
    public CategoryResponseDTO getCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
        return new CategoryResponseDTO(category);
    }

    // 모든 카테고리 조회 - 정렬 필요
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponseDTO::new)
                .collect(Collectors.toList());
    }

}
