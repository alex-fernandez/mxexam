package com.alexfrndz.orderexam.config;

import com.alexfrndz.orderexam.entity.OrderItemEntity;
import com.alexfrndz.orderexam.pojo.OrderItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderItemPojoToOrderItemEntityConverter implements Converter<OrderItem, OrderItemEntity> {

    @Override
    public OrderItemEntity convert(OrderItem source) {
        OrderItemEntity target = new OrderItemEntity();
        target.setItemId(source.getId());
        target.setCount(source.getCount());
        return target;
    }
}
