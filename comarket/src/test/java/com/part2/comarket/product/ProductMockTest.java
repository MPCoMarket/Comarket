package com.part2.comarket.product;

import com.part2.comarket.ApiTest;
import com.part2.comarket.category.command.domain.Category;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductMockTest extends ApiTest {

    @InjectMocks
    private AddProductService addProductService;

    @InjectMocks
    private UpdateProductService updateProductService;

    @InjectMocks
    private DeleteProductService deleteProductService;

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void 상품_등록() {
        //given
        final ProductPostRequestDTO request = 상품_등록_요청();
        Product product = new Product(request.title(), request.price(), request.content(), request.category());

        when(productRepository.save(any(Product.class))).thenReturn(product);

        //when
        addProductService.addProduct(request);

        //then
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void 상품_수정() {
        //given
        final ProductPatchRequestDTO request = 상품_수정_요청();
        final Product oldProduct = createProduct();
        when(productRepository.findById(oldProduct.getId())).thenReturn(Optional.of(oldProduct));

        //when
        updateProductService.updateProduct(oldProduct.getId(), request);

        //then
        verify(productRepository, times(1)).findById(oldProduct.getId());

        assertThat(oldProduct.getTitle()).isEqualTo(request.title());
        assertThat(oldProduct.getPrice()).isEqualTo(request.price());
        assertThat(oldProduct.getContent()).isEqualTo(request.content());
    }

    @Test
    void 상품_삭제() {
        //given
        final Product product = createProduct();
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        //when
        deleteProductService.deleteProduct(product.getId());

        //then
        verify(productRepository, times(1)).deleteById(product.getId());
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> productService.getProduct(product.getId()));
    }

    private static ProductPostRequestDTO 상품_등록_요청() {
        final String title = "코마켓 상품";
        final int price = 19900;
        final String content = "z";
        final Category category = new Category("전자기기", null);

        return new ProductPostRequestDTO(title, price, content, category);
    }

    private static ProductPatchRequestDTO 상품_수정_요청() {
        return ProductPatchRequestDTO.builder()
                .title("수정할게요. 코마켓 상품")
                .price(12000)
                .content("할인했어요")
                .build();
    }

    private Product createProduct() {
        final ProductPostRequestDTO request = 상품_등록_요청();
        return addProductService.addProduct(request);
    }
}