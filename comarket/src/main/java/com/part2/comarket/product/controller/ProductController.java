package com.part2.comarket.product.controller;


import com.part2.comarket.product.command.application.AddProductService;
import com.part2.comarket.product.command.application.DeleteProductService;
import com.part2.comarket.product.command.application.UpdateProductService;
import com.part2.comarket.product.command.dto.request.ProductPatchRequestDTO;
import com.part2.comarket.product.command.dto.request.ProductPostRequestDTO;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final AddProductService addProductService;
    private final UpdateProductService updateProductService;
    private final DeleteProductService deleteProductService;

    @PostMapping
    public ResponseEntity<Void> addProduct(@Valid @RequestBody final ProductPostRequestDTO requestDto)
    {
        addProductService.addProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable final Long productId, @Valid @RequestBody final ProductPatchRequestDTO requestDto)
    {
        updateProductService.updateProduct(productId, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable final Long productId)
    {
        deleteProductService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}
