package com.alexfrndz.orderexam.config;

import com.alexfrndz.orderexam.entity.ItemEntity;
import com.alexfrndz.orderexam.entity.OrderEntity;
import com.alexfrndz.orderexam.pojo.Item;
import com.alexfrndz.orderexam.pojo.OrderRequest;
import com.alexfrndz.orderexam.utils.ConverterBasedTransformer;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;


@Configuration
@EnableAutoConfiguration
public class ConversionConfig {

    @Autowired
    private OrderPojoToOrderEntityConverter orderPojoToOrderEntityConverter;

    @Autowired
    private OrderEntityToOrderPojoConverter orderEntityToOrderPojoConverter;

    @Autowired
    private OrderItemPojoToOrderItemEntityConverter orderItemPojoToOrderItemEntityConverter;

    @Autowired
    private ItemEntityToItemPojoConverter itemEntityToItemPojoConverter;

    @Autowired
    private ItemPojoToItemEntityConverter itemPojoToItemEntityConverter;


    @Bean
    @Qualifier("orderExamConversionService")
    public ConversionService mondelasportsConversionService() {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        Set<Converter> converters = Sets.newHashSet();
        converters.add(orderPojoToOrderEntityConverter);
        converters.add(orderEntityToOrderPojoConverter);
        converters.add(orderItemPojoToOrderItemEntityConverter);
        converters.add(itemEntityToItemPojoConverter);
        converters.add(itemPojoToItemEntityConverter);
        factory.setConverters(converters);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean(name = "orderEntityToOrderPojoConverterBasedTransformer")
    public ConverterBasedTransformer<OrderEntity, OrderRequest> orderEntityToOrderPojoConverterBasedTransformer() {
        return new ConverterBasedTransformer<>(OrderRequest.class);

    }


    @Bean(name = "itemEntityItemConverterBasedTransformer")
    public ConverterBasedTransformer<ItemEntity, Item> itemEntityItemConverterBasedTransformer() {
        return new ConverterBasedTransformer<>(Item.class);

    }


}