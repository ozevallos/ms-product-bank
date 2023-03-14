package com.nttdata.product.service.imp;


import com.nttdata.product.dto.ProductDto;
import com.nttdata.product.entity.Product;
import com.nttdata.product.repository.ProductRepository;
import com.nttdata.product.service.ProductService;

import com.nttdata.product.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Flux<ProductDto> findAll() {
        return productRepository.findAll().map(AppUtils::entityToDto);
    }

    @Override
    public Mono<ProductDto> findById(String id) {
        return productRepository.findById(id).map(AppUtils::entityToDto);
    }

    @Override
    public Mono<ProductDto> save(Mono<ProductDto> productDtoMono) {

        return productDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToDto);
    }

    @Override
    public Mono<ProductDto> update(Mono<ProductDto> productDtoMono, String id) {
        return productRepository.findById(id)
                .flatMap(p->productDtoMono.map(AppUtils::dtoToEntity))
                .doOnNext(e->e.setId(id))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        return productRepository.deleteById(id);
    }
}
