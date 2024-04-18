package com.part2.comarket.product.command.dto.request;

import lombok.Builder;

@Builder
public record ProductPatchRequestDTO(
        String title,
        int price,
        String content,
        String tradingLocation
        ) {
}