package com.part2.comarket.product;

import com.part2.comarket.product.command.application.ProductService;
import com.part2.comarket.product.command.dto.request.ProductPostRequestDTO;
import com.part2.comarket.product.command.repository.ProductRepository;
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
        final ProductPostRequestDTO request = 상품_등록_요청();
        productService.addProduct(request);
    }

    private static ProductPostRequestDTO 상품_등록_요청() {
        final String title = "코마켓 상품";
        final int price = 19900;
        final String content = "z";

        return new ProductPostRequestDTO(title, price, content);
    }

}