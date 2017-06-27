package com.alexfrndz.orderexam.repository;

import com.alexfrndz.orderexam.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {


}
