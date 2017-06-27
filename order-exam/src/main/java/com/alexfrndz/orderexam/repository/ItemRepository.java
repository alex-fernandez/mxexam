package com.alexfrndz.orderexam.repository;

import com.alexfrndz.orderexam.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long>, ItemRepositoryCustom {


}
