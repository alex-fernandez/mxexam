package com.alexfrndz.orderexam.config;


import com.alexfrndz.orderexam.entity.ItemEntity;
import com.alexfrndz.orderexam.pojo.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemEntityToItemPojoConverter implements Converter<ItemEntity, Item> {

    @Override
    public Item convert(ItemEntity source) {
        Item target = new Item();
        target.setId(source.getId());
        target.setCost(source.getCost());
        target.setName(source.getName());
        target.setUpdatedOn(source.getUpdatedOn());
        target.setCreatedOn(source.getCreatedOn());
        return target;
    }

}
