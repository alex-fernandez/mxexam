package com.alexfrndz.orderexam.config;


import com.alexfrndz.orderexam.entity.OrderEntity;
import com.alexfrndz.orderexam.entity.OrderItemEntity;
import com.alexfrndz.orderexam.pojo.OrderRequest;
import com.alexfrndz.orderexam.pojo.OrderItem;
import com.google.common.collect.Sets;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OrderEntityToOrderPojoConverter implements Converter<OrderEntity, OrderRequest> {

    @Override
    public OrderRequest convert(OrderEntity source) {
        OrderRequest target = new OrderRequest();
        target.setId(source.getId());
        target.setCustomerName(source.getCustomerName());
        target.setPlacementDate(source.getPlacementDate());
        target.setCreatedOn(source.getCreatedOn());
        target.setUpdatedOn(source.getUpdatedOn());


        Set<OrderItem> orderItemSet = Sets.newHashSet();
        for (OrderItemEntity itemEntity : source.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(itemEntity.getId());
            orderItem.setCount(itemEntity.getCount());
            orderItem.setItemCost(itemEntity.getCost());
            orderItemSet.add(orderItem);
        }

        target.setItems(orderItemSet);

        return target;
    }

}
