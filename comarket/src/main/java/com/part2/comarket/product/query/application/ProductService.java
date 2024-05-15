package com.part2.comarket.product.query.application;

import com.part2.comarket.product.command.domain.Product;
import com.part2.comarket.product.command.dto.request.ProductPostRequestDTO;
import com.part2.comarket.product.command.repository.ProductRepository;
import com.part2.comarket.product.query.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;

    /**
     * 상품 상세 조회
     * @param id
     * @return ProductDTO
     */
    public ProductDTO getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("상품을 찾을 수 없습니다.")
        );
        return ProductDTO.fromEntity(product);
    }

    /**g
     * @param page
     * @param size
     * @return Page<ProductDTO>
     */
    public Page<ProductDTO> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);

        return productPage.map(ProductDTO::fromEntity);
    }
}
