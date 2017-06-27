package com.alexfrndz.orderexam.config;


import com.alexfrndz.orderexam.entity.OrderEntity;
import com.alexfrndz.orderexam.pojo.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToProductPojoConverter implements Converter<OrderEntity, Order> {

    @Override
    public Order convert(OrderEntity source) {
        Order target = new Order();
        target.setId(source.getId());
        return target;
    }

}
