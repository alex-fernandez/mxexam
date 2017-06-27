package com.alexfrndz.orderexam.repository;

import com.alexfrndz.orderexam.entity.ItemEntity;
import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositoryCustom {

    SearchResponse<ItemEntity> search(String customerName, PaginationSearchRequest searchRequest);



}
