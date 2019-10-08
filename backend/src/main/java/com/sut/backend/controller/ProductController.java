package com.sut.backend.controller;

import com.sut.backend.entity.Product;
import com.sut.backend.entity.Type;
import com.sut.backend.repository.ProductRepository;
import com.sut.backend.repository.TypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeRepository typeRepository;

   

    public  ProductController(ProductRepository productRepository, TypeRepository typeRepository){ 
        this.typeRepository = typeRepository;
        this.productRepository = productRepository;
    }

    //Get all data in table 
    @PostMapping("/products")
    public Collection<Product> getDoctors(@RequestBody Map<String,String>body){
        System.out.println("Contrlloer");
        String code=body.get("code").toString();
        String name=body.get("name").toString();
        String image=body.get("image").toString();
        String detail=body.get("detail").toString();
        long types=Long.valueOf(body.get("types"));
        Type t = typeRepository.findById(types);

        Product  product = new Product();
        product.setCode(code);
        product.setImage(image);
        product.setName(name);
        product.setType(t);
        product.setDetail(detail);
        productRepository.save(product);
        return productRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/product")
    public Collection<Product> getDoctors(){
        return productRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    public Product getp(@PathVariable long id){
        return productRepository.findById(id);
    }

    @PostMapping("/productedit/{id}")
    public Collection<Product> geteditProduct(@RequestBody Map<String,String>body, @PathVariable long id){
        String code=body.get("code").toString();
        String name=body.get("name").toString();
        String image=body.get("image").toString();
        String detail=body.get("detail").toString();

        Product  product = productRepository.findById(id);
        //product.setCode(code);
        if (!image.equals("")) {
            product.setImage(image);
        }
        if (!code.equals("")) {
            product.setCode(code);
        }
        if (!name.equals("")) {
            product.setName(name);
        }
        
        if (!body.get("types").equals("")) {
            long types=Long.valueOf(body.get("types"));

            Type t = typeRepository.findById(types);

            product.setType(t);
        }
        //product.setName(name);
        //product.setType(t);
        if (!detail.equals("")) {
            product.setDetail(detail);
        }
        
        //product.setDetail(detail);
        productRepository.save(product);
        return productRepository.findAll().stream().collect(Collectors.toList());
    }

    @DeleteMapping("/deleteproduct/{id}")
    public boolean delete(@PathVariable long id){
        productRepository.deleteById(id);
        if(productRepository.findById(id) != null) {
            return false;
        } else {
            return true;
        }
    }
}