package com.part2.comarket.product.command.application;

import com.part2.comarket.product.command.domain.Product;
import com.part2.comarket.product.command.dto.request.ProductPostRequestDTO;
import com.part2.comarket.product.command.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Product addProduct(ProductPostRequestDTO request) {
        return productRepository.save(request.toEntity());
    }
}
