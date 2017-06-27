package com.alexfrndz.orderexam.service;

import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.Item;
import com.alexfrndz.orderexam.pojo.SearchResponse;

public interface IProduct {

    SearchResponse search(String name, PaginationSearchRequest searchRequest);

    Item get(Long productId);

    Item create(Item request);

    Item update(Long productId, Item request);

    void delete(Long productId);
}
