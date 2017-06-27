package com.alexfrndz.orderexam.service;

import com.alexfrndz.orderexam.pojo.OrderRequest;
import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import org.hibernate.criterion.Order;

public interface IOrder {

    SearchResponse search(String customerName, PaginationSearchRequest searchRequest);

    Order get(Long entityId);

    Order create(OrderRequest request);

    Order update(Long entityId, OrderRequest request);

    void delete(Long entityId);
}
