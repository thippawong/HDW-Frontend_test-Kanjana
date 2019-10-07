package com.sut.backend.repository;
import com.sut.backend.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Product findByProductsID(Long productsID);
    Product findById(long productsID);
}