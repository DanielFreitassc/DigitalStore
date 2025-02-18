package com.danielfreitassc.backend.services;

import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.ProductRequestDto;
import com.danielfreitassc.backend.dtos.ProductResponseDto;
import com.danielfreitassc.backend.mappers.ProductMapper;
import com.danielfreitassc.backend.models.ProductEntity;
import com.danielfreitassc.backend.repositories.ProductRepository;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final MinioClient minioClient;
    private final Tika tika;

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) throws Exception {
        onlyImage(productRequestDto);
        String imageId = UUID.randomUUID().toString();
        ProductEntity productEntity = productMapper.toEntity(productRequestDto);
        productEntity.setImageId(imageId);

        minioClient.putObject(
          PutObjectArgs.builder()
          .bucket("images")
          .object(imageId)
          .stream(productRequestDto.image().getInputStream(), productRequestDto.image().getSize(), -1)
          .contentType(productRequestDto.image().getContentType())
          .build()  
        );

        return productMapper.toDto(productRepository.save(productEntity));
    }

    public Page<ProductResponseDto> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }

    public ProductResponseDto getProduct(String id) {
        return productMapper.toDto(checkProductId(id));
    }

    public ProductResponseDto updateProduct(String id, ProductRequestDto productRequestDto) throws Exception {
        onlyImage(productRequestDto);
        ProductEntity productEntity = checkProductId(id);

        if(productRequestDto.image() != null) {
            String imageId = UUID.randomUUID().toString();

            minioClient.putObject(
                PutObjectArgs.builder()
                .bucket("images")
                .object(imageId)
                .stream(productRequestDto.image().getInputStream(), productRequestDto.image().getSize(), -1)
                .contentType(productRequestDto.image().getContentType())
                .build()
            );

            minioClient.removeObject(
                RemoveObjectArgs.builder()
                .bucket("images")
                .object(productEntity.getImageId())
                .build()
            );

            productEntity.setImageId(imageId);
        }

        productMapper.toUpdateDto(productRequestDto, productEntity);
        return productMapper.toDto(productRepository.save(productEntity));
    }    

    public ProductResponseDto deleteProduct(String id) throws Exception {
        ProductEntity productEntity = checkProductId(id);

        minioClient.removeObject(
            RemoveObjectArgs.builder()
            .bucket("images")
            .object(productEntity.getImageId())
            .build()
        );

        productRepository.delete(productEntity);
        return productMapper.toDto(productEntity);
    }

    private ProductEntity checkProductId(String id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if(product.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum produto encontrado");
        return product.get();
    }

    private void onlyImage(ProductRequestDto productRequestDto) throws Exception {
        try (InputStream inputStream = productRequestDto.image().getInputStream()) {
            String image = tika.detect(inputStream);

            if(!image.startsWith("image/"))  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Apenas imagens são permitidas");
        } catch (Exception e) {
            if(e instanceof ResponseStatusException) throw e;
            
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro desconhecido ao processar imagem!");
        }
    }
}
