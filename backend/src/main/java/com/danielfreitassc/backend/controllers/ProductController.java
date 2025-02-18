package com.danielfreitassc.backend.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
