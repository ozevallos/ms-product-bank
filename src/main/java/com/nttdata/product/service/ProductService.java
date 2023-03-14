package com.nttdata.product.service;


import com.nttdata.product.dto.ProductDto;
import com.nttdata.product.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductDto> findAll();
    Mono<ProductDto> findById(String id);
    Mono<ProductDto> save(Mono<ProductDto> productDtoMono);
    Mono<ProductDto> update(Mono<ProductDto> productDtoMono, String id);
    Mono<Void> delete(String id);

}
