package com.example.ecom.controller;

import com.example.ecom.model.Product;
import com.example.ecom.payload.ApiResponse;
import com.example.ecom.payload.ProductRequest;
import com.example.ecom.repository.UserRepository;
import com.example.ecom.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductController productController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);


    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> insertProduct(@Valid @RequestBody ProductRequest productRequest) {

        Product product = productService.addProduct(productRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productID}")
                .buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Product Stored Successfully"));

    }
}
