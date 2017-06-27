package com.alexfrndz.orderexam.config;

import com.alexfrndz.orderexam.entity.OrderEntity;
import com.alexfrndz.orderexam.pojo.Order;
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

    @Bean
    @Qualifier("orderExamConversionService")
    public ConversionService mondelasportsConversionService() {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        Set<Converter> converters = Sets.newHashSet();
        converters.add(orderPojoToOrderEntityConverter);
        converters.add(orderEntityToOrderPojoConverter);
        converters.add(orderItemPojoToOrderItemEntityConverter);
        factory.setConverters(converters);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean(name = "orderPojoToOrderEntityConverterBasedTransformer")
    public ConverterBasedTransformer<OrderEntity, Order> orderPojoToOrderEntityConverterBasedTransformer() {
        return new ConverterBasedTransformer<>(Order.class);

    }


}