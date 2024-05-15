package com.part2.comarket.category.controller;

import com.part2.comarket.category.command.application.AddCategoryService;
import com.part2.comarket.category.command.domain.Category;
import com.part2.comarket.category.command.dto.CategoryPostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final AddCategoryService addCategoryService;

    @PostMapping
    public Category createCategory(@RequestBody CategoryPostRequestDTO categoryPostRequestDTO) {
        return addCategoryService.addCategory(categoryPostRequestDTO);
    }

}