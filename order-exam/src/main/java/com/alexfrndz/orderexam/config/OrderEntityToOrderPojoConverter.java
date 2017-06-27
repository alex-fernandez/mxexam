package com.alexfrndz.orderexam.config;


import com.alexfrndz.orderexam.entity.OrderEntity;
import com.alexfrndz.orderexam.pojo.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityToOrderPojoConverter implements Converter<OrderEntity, Order> {

    @Override
    public Order convert(OrderEntity source) {
        Order target = new Order();
        target.setId(source.getId());
        target.setCustomerName(source.getCustomerName());
        target.setPlacementDate(source.getPlacementDate());
        target.setCreatedOn(source.getCreatedOn());
        target.setUpdatedOn(source.getUpdatedOn());
        return target;
    }

}
