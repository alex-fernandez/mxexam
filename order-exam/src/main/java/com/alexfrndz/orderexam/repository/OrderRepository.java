package com.alexfrndz.orderexam.repository;

import com.alexfrndz.orderexam.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long>, OrderRepositoryCustom {


}
