package com.part2.comarket.category.command.application;

import com.part2.comarket.category.command.domain.Category;
import com.part2.comarket.category.command.repository.CategoryRepository;
import com.part2.comarket.common.exception.CustomException;
import com.part2.comarket.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class DeleteCategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
        categoryRepository.delete(category);
    }
}
