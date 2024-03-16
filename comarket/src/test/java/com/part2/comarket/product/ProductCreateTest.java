package com.part2.comarket.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProductCreateTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;
    @Test
    void 상품_등록() {
        final ProductPostDTO request = 상품_등록_요청();
        productService.addProduct(request);
    }

    private static ProductPostDTO 상품_등록_요청() {

    }

}