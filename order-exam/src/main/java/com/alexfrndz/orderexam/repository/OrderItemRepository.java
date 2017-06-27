package com.alexfrndz.orderexam.repository;

import com.alexfrndz.orderexam.entity.OrderEntity;
import com.alexfrndz.orderexam.entity.OrderItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItemEntity, Long> {

    List<OrderItemEntity> findAllByOrder(OrderEntity order);

}
