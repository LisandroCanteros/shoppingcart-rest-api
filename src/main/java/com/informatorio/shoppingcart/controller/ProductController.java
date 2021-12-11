package com.informatorio.shoppingcart.controller;

import com.informatorio.shoppingcart.entity.Product;
import com.informatorio.shoppingcart.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity(productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){
        Optional<Product> productFound = productRepository.findById(id);
        if (productFound.isPresent()) {
            return new ResponseEntity(productFound, HttpStatus.OK);
        }
        return new ResponseEntity("Product not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        return new ResponseEntity(productRepository.save(product), HttpStatus.CREATED);
    }
}
