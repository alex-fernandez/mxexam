package com.alexfrndz.orderexam.config;

import com.alexfrndz.orderexam.entity.ProductEntity;
import com.alexfrndz.orderexam.pojo.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductPojoToProductEntityConverter implements Converter<Product, ProductEntity> {

    @Override
    public ProductEntity convert(Product source) {
        ProductEntity target = new ProductEntity();
        return target;
    }
}
