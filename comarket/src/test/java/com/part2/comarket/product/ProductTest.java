package com.part2.comarket.product;

import com.part2.comarket.ApiTest;
import com.part2.comarket.common.exception.CustomException;
import com.part2.comarket.product.command.application.AddProductService;
import com.part2.comarket.product.command.application.DeleteProductService;
import com.part2.comarket.product.command.application.UpdateProductService;
import com.part2.comarket.product.command.domain.Product;
import com.part2.comarket.product.command.dto.request.ProductPatchRequestDTO;
import com.part2.comarket.product.command.dto.request.ProductPostRequestDTO;
import com.part2.comarket.product.command.repository.ProductRepository;
import com.part2.comarket.product.query.application.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductTest extends ApiTest {

    @Autowired
    private AddProductService addProductService;
    @Autowired
    private UpdateProductService updateProductService;
    @Autowired
    private DeleteProductService deleteProductService;
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    @Test
    void 상품_등록() {
        final ProductPostRequestDTO request = 상품_등록_요청();
        addProductService.addProduct(request);
    }

    @Test
    void 상품_수정() {
        //given
        final ProductPatchRequestDTO request = 상품_수정_요청();

        final Product oldProduct = addProductService.addProduct(상품_등록_요청());
        when(productRepository.findById(oldProduct.getId())).thenReturn(Optional.of(oldProduct));

        //when
        updateProductService.updateProduct(oldProduct.getId(), request);
        //then
        // times : 몇번 실행되었는지 체크
        verify(productRepository, times(1)).findById(oldProduct.getId());

        assertThat(oldProduct.getTitle()).isEqualTo(request.title());
        assertThat(oldProduct.getPrice()).isEqualTo(request.price());
        assertThat(oldProduct.getContent()).isEqualTo(request.content());
    }

    @Test
    void 상품_삭제() {
        //given
        final Product product = addProductService.addProduct(상품_등록_요청());
        //when
        deleteProductService.deleteProduct(product.getId());
        //then
        assertThrows(CustomException.class, () -> productService.getProduct(product.getId()));
    }

    private static ProductPostRequestDTO 상품_등록_요청() {
        final String title = "코마켓 상품";
        final int price = 19900;
        final String content = "z";

        return new ProductPostRequestDTO(title, price, content);
    }

    // 상품 수정 요청 객체
    private static ProductPatchRequestDTO 상품_수정_요청() {
        return ProductPatchRequestDTO.builder()
                .title("수정할게요. 코마켓 상품")
                .price(12000)
                .content("할인했어요")
                .build();
    }

}