package com.nttdata.product.controller;

import com.nttdata.product.dto.ProductDto;
import com.nttdata.product.entity.Product;
import com.nttdata.product.service.ProductService;
import com.nttdata.product.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public Flux<ProductDto> findAll(){
        return productService.findAll();

    }

    @GetMapping("/{id}")
    public Mono<ProductDto> findProduct(@PathVariable String id){
        return productService.findById(id);
    }

    @PostMapping
    public Mono<ProductDto> save(@RequestBody Mono<ProductDto> productDtoMono){
        return productService.save(productDtoMono);
    }

    @PutMapping("/{id}")
    public Mono<ProductDto> update(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String id){
        return productService.update(productDtoMono,id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return productService.delete(id);
    }
}
