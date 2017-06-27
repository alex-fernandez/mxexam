package com.alexfrndz.orderexam.service;

import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.Product;
import com.alexfrndz.orderexam.pojo.SearchResponse;

public interface IProduct {

    SearchResponse search(String name, PaginationSearchRequest searchRequest);

    Product get(Long productId);

    Product create(Product request);

    Product update(Long productId, Product request);

    void delete(Long productId);
}
