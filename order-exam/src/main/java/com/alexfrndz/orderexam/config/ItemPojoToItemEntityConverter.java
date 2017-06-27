package com.alexfrndz.orderexam.config;

import com.alexfrndz.orderexam.entity.ItemEntity;
import com.alexfrndz.orderexam.pojo.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemPojoToItemEntityConverter implements Converter<Item, ItemEntity> {

    @Override
    public ItemEntity convert(Item source) {
        ItemEntity target = new ItemEntity();
        target.setCost(source.getCost());
        target.setName(source.getName());
        return target;
    }
}
