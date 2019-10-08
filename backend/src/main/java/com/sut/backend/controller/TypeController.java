package com.sut.backend.controller;

import com.sut.backend.entity.Type;
import com.sut.backend.repository.TypeRepository;
import com.sut.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TypeController {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeRepository typeRepository;

   

    public  TypeController(ProductRepository productRepository, TypeRepository typeRepository){ 
        this.typeRepository = typeRepository;
        this.productRepository = productRepository;
    }

    //Get all data in table 
    @GetMapping("/types")
    public Collection<Type> getDoctors(){
        return typeRepository.findAll().stream().collect(Collectors.toList());
    }
}