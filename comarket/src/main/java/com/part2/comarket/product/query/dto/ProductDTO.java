package com.part2.comarket.product.query.dto;

import com.part2.comarket.product.command.domain.Product;

import java.util.Objects;

public record ProductDTO(
        Long id,
        String title,
        String content,
        int price,
        String tradingLocation
) {

    public ProductDTO(Product product) {
        this(Objects.requireNonNull(product.getId()),
                product.getTitle(),
                product.getContent(),
                product.getPrice(),
                product.getTradingLocation());
    }
    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(product);
    }
}
