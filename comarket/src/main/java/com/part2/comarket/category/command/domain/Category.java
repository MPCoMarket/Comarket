package com.part2.comarket.category.command.domain;

import com.part2.comarket.category.command.application.dto.CategoryRequestDTO;
import com.part2.comarket.common.entity.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter
public class Category extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 부모 카테고리 아이디
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parentId;
    // 카테고리 이름
    private String name;

    public Category(String name, @Nullable  Category parentCategory) {
        this.name = name;
        if(parentCategory != null) {
            this.parentId = parentCategory;
        }
    }

    public void update(CategoryRequestDTO requestDto) {
        this.name = requestDto.name();
        if (requestDto.parentId() != null) {

        }
    }
}
