package br.com.danielfreitassc.service.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielfreitassc.service.dtos.ProductRecordDTO;
import br.com.danielfreitassc.service.models.ProductEntity;
import br.com.danielfreitassc.service.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private ProductService productService;


    @PostMapping
    public ResponseEntity<ProductEntity> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
        return productService.saveProduct(productRecordDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProucts() {
    return productService.getAllProduct();
    }

   @GetMapping("{id}")
   public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id")Long id) {
    return productService.getOneProduct(id);
   }

   @PutMapping("{id}")
   public ResponseEntity<Object> updateProduct(@PathVariable(value = "id")Long id, @RequestBody @Valid ProductRecordDTO productRecordDTO) {
    return productService.updateProduct(id, productRecordDTO);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id")Long id) {
    return productService.deleteProduct(id);
   }


}
