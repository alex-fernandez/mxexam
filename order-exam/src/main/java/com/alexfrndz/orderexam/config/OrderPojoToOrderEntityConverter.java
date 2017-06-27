package com.alexfrndz.orderexam.config;

import com.alexfrndz.orderexam.entity.OrderEntity;
import com.alexfrndz.orderexam.pojo.OrderRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderPojoToOrderEntityConverter implements Converter<OrderRequest, OrderEntity> {

    @Override
    public OrderEntity convert(OrderRequest source) {
        OrderEntity target = new OrderEntity();
        target.setCustomerName(source.getCustomerName());
        target.setPlacementDate(source.getPlacementDate());
        return target;
    }
}
