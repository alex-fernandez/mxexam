package com.alexfrndz.orderexam.repository;

import com.alexfrndz.orderexam.entity.OrderItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItemEntity, Long> {


}
