package com.alexfrndz.orderexam.repository;

import com.alexfrndz.orderexam.entity.OrderEntity;
import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryCustom {

    SearchResponse<OrderEntity> search(String customerName, PaginationSearchRequest searchRequest);



}
