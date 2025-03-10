package com.danielfreitassc.backend.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.danielfreitassc.backend.dtos.ProductRequestDto;
import com.danielfreitassc.backend.dtos.ProductResponseDto;
import com.danielfreitassc.backend.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto createProduct(@ModelAttribute @Valid ProductRequestDto productRequestDto) throws Exception {
        return productService.createProduct(productRequestDto);
    }

    @GetMapping
    public Page<ProductResponseDto> getProducts(Pageable pageable) {
        return productService.getProducts(pageable);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable String id,@ModelAttribute @Valid ProductRequestDto productRequestDto) throws Exception {
        return productService.updateProduct(id, productRequestDto);
    }

    @DeleteMapping("/{id}")
    public ProductResponseDto deleteProduct(@PathVariable String id) throws Exception {
        return productService.deleteProduct(id);
    }
}
