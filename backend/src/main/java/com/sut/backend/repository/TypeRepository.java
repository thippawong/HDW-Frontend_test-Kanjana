package com.sut.backend.repository;
import com.sut.backend.entity.Type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findById(long typesID);
    //Type findByTypesID(Long typesID);
}