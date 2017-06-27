package com.alexfrndz.orderexam.config;

import com.alexfrndz.orderexam.entity.OrderEntity;
import com.alexfrndz.orderexam.pojo.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderPojoToOrderEntityConverter implements Converter<Order, OrderEntity> {

    @Override
    public OrderEntity convert(Order source) {
        OrderEntity target = new OrderEntity();
        target.setCustomerName(source.getCustomerName());
        target.setPlacementDate(source.getPlacementDate());
        return target;
    }
}
