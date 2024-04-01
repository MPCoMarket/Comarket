package com.part2.comarket.product.controller;


import com.part2.comarket.product.command.application.AddProductService;
import com.part2.comarket.product.command.application.DeleteProductService;
import com.part2.comarket.product.command.application.UpdateProductService;
import com.part2.comarket.product.command.dto.request.ProductPatchRequestDTO;
import com.part2.comarket.product.command.dto.request.ProductPostRequestDTO;
import com.part2.comarket.product.query.application.ProductService;
import com.part2.comarket.product.query.dto.ProductDTO;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final AddProductService addProductService;
    private final UpdateProductService updateProductService;
    private final DeleteProductService deleteProductService;

    /**
     * 상품 상세 조회
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable final Long productId) {
        return  ResponseEntity.ok(productService.getProduct(productId));
    }

    /**
     * 상품 목록 조회
     */
    @GetMapping("/")
    public ResponseEntity<Page<ProductDTO>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return  ResponseEntity.ok(productService.getProducts(page, size));
    }

    /**
     * 상품 생성
     */
    @PostMapping
    public ResponseEntity<Void> addProduct(@Valid @RequestBody final ProductPostRequestDTO requestDto)
    {
        addProductService.addProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 상품 수정
     */
    @PatchMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable final Long productId, @Valid @RequestBody final ProductPatchRequestDTO requestDto)
    {
        updateProductService.updateProduct(productId, requestDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 상품 삭제
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable final Long productId)
    {
        deleteProductService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}
