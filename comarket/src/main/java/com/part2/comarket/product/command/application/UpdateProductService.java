package com.part2.comarket.product.command.application;

import com.part2.comarket.product.command.domain.Product;
import com.part2.comarket.product.command.dto.request.ProductPatchRequestDTO;
import com.part2.comarket.product.command.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void updateProduct(Long productId, ProductPatchRequestDTO request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("수정하려는 상품 정보가 없습니다.")); // to do : 예외처리
        product.update(request);
    }
}
