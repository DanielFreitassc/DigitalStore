package com.danielfreitassc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,String> {
    
}
