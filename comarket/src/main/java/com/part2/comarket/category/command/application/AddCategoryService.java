package com.part2.comarket.category.command.application;


import com.part2.comarket.category.command.domain.Category;
import com.part2.comarket.category.command.dto.CategoryPostRequestDTO;
import com.part2.comarket.category.command.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddCategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public Category addCategory(CategoryPostRequestDTO request) {
        Category parentCategory = categoryRepository.findById(request.parentId()).orElse(null);
        return categoryRepository.save(request.toEntity(parentCategory));
    }
}
