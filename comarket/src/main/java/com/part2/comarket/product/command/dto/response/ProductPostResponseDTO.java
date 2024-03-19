package com.part2.comarket.product.command.dto.response;

import jakarta.validation.constraints.NotBlank;

public record ProductPostResponseDTO(
        @NotBlank(message = "상품 제목은 필수입니다.")
        String title,
        @NotBlank(message = "상품 가격은 필수입니다.")
        String price,
        String content,
        String categoryId
        ) {

}