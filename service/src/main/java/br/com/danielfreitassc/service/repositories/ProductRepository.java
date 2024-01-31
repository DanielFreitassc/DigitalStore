package br.com.danielfreitassc.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.danielfreitassc.service.models.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository <ProductEntity,Long>{

    
}
