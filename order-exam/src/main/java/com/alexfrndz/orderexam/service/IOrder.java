package com.alexfrndz.orderexam.service;

import com.alexfrndz.orderexam.pojo.Order;
import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.SearchResponse;

public interface IOrder {

    SearchResponse search(String customerName, PaginationSearchRequest searchRequest);

    Order get(Long entityId);

    Order create(Order request);

    Order update(Long entityId, Order request);

    void delete(Long entityId);
}
