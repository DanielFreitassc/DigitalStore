package br.com.danielfreitassc.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.danielfreitassc.service.controllers.ProductController;
import br.com.danielfreitassc.service.dtos.ProductRecordDTO;
import br.com.danielfreitassc.service.models.ProductEntity;
import br.com.danielfreitassc.service.repositories.ProductRepository;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductRepository productRepository;

    // Salvar produto
    public ResponseEntity<ProductEntity> saveProduct(ProductRecordDTO productRecordDTO) {
        var productEntity = new ProductEntity();
        BeanUtils.copyProperties(productRecordDTO, productEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productEntity));
    }

    // Buscar todos os produtos
    public ResponseEntity<List<ProductEntity>> getAllProduct() {
        List<ProductEntity> productList = productRepository.findAll();
        if(!productList.isEmpty()) {
            for(ProductEntity productEntity: productList) {
                Long id = productEntity.getId();
                productEntity.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }
    
    // Buscar produto por id
    public ResponseEntity<Object> getOneProduct(Long id) {
        Optional<ProductEntity> productOpt = productRepository.findById(id);
    if(productOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum produto com este id");
    }
    productOpt.get().add(linkTo(methodOn(ProductController.class).getAllProucts()).withRel("Lista de produtos"));
    return ResponseEntity.status(HttpStatus.OK).body(productOpt);
    }

    // Remover produto por id
    public ResponseEntity<Object> deleteProduct(Long id) {
        Optional<ProductEntity> productOpt = productRepository.findById(id);
        if(productOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum produto com este id");
        }
        productRepository.delete(productOpt.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto removido com sucesso");
    }

    // Atualizar produto por id
    public ResponseEntity<Object> updateProduct(Long id, ProductRecordDTO productRecordDTO) {
        Optional<ProductEntity> productOpt = productRepository.findById(id);
        if(productOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum produto com este id encontrado");
        }
        var productEntity = productOpt.get();
        BeanUtils.copyProperties(productRecordDTO, productEntity);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productEntity));
    }

}
