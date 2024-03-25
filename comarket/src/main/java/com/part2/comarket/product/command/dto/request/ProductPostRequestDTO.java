package com.part2.comarket.product.command.dto.request;

import com.part2.comarket.product.command.domain.Product;
import jakarta.validation.constraints.NotBlank;

public record ProductPostRequestDTO(
        @NotBlank(message = "상품 제목은 필수입니다.")
        String title,
        @NotBlank(message = "상품 가격은 필수입니다.")
        int price,
        String content
        ) {

        public Product toEntity() {
                return new Product(title, price, content);
        }
}