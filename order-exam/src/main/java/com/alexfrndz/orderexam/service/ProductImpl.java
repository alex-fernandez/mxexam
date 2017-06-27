package com.alexfrndz.orderexam.service;

import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.Product;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import com.alexfrndz.orderexam.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductImpl extends AbstractService implements IProduct {

    private ProductRepository productRepository;


    @Autowired
    public ProductImpl(
            ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    @Override
    public SearchResponse search(String name, PaginationSearchRequest searchRequest) {
        return null;
    }

    @Override
    public Product get(Long productId) {
        return null;
    }

    @Override
    public Product create(Product request) {
        return null;
    }

    @Override
    public Product update(Long productId, Product request) {
        return null;
    }

    @Override
    public void delete(Long productId) {

    }
}
