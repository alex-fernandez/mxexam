package com.alexfrndz.orderexam.service;

import com.alexfrndz.orderexam.config.OrderItemPojoToOrderItemEntityConverter;
import com.alexfrndz.orderexam.entity.ItemEntity;
import com.alexfrndz.orderexam.entity.OrderEntity;
import com.alexfrndz.orderexam.entity.OrderItemEntity;
import com.alexfrndz.orderexam.pojo.Order;
import com.alexfrndz.orderexam.pojo.OrderItem;
import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import com.alexfrndz.orderexam.pojo.exception.ApiException;
import com.alexfrndz.orderexam.pojo.exception.NotFoundException;
import com.alexfrndz.orderexam.repository.OrderItemRepository;
import com.alexfrndz.orderexam.repository.OrderRepository;
import com.alexfrndz.orderexam.utils.ConverterBasedTransformer;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class OrderImpl extends AbstractService implements IOrder {

    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private ConverterBasedTransformer<OrderEntity, Order> entityPojoToEntityEntityConverterBasedTransformer;
    private OrderItemPojoToOrderItemEntityConverter orderItemPojoToOrderItemEntityConverter;
    private ItemImpl itemService;

    private static final String NOT_FOUND_ERROR_STATUS = "Not Found";
    private static final String NOT_FOUND_ERROR_MESSAGE = "No Order Found";

    @Autowired
    public OrderImpl(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            @Qualifier("orderEntityToOrderPojoConverterBasedTransformer")
                    ConverterBasedTransformer<OrderEntity, Order> CustomerPojoToCustomerEntityConverterBasedTransformer,
            OrderItemPojoToOrderItemEntityConverter orderItemPojoToOrderItemEntityConverter,
            ItemImpl itemService
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.entityPojoToEntityEntityConverterBasedTransformer = CustomerPojoToCustomerEntityConverterBasedTransformer;
        this.orderItemPojoToOrderItemEntityConverter = orderItemPojoToOrderItemEntityConverter;
        this.itemService = itemService;

    }

    @Override
    public SearchResponse search(String customerName, PaginationSearchRequest searchRequest) {
        SearchResponse<OrderEntity> searchResponse = orderRepository.search(customerName, searchRequest);
        return searchResponseConverter.buildSearchResponse(searchResponse, entityPojoToEntityEntityConverterBasedTransformer);
    }

    @Override
    public Order get(Long entityId) {
        OrderEntity orderEntity = orderRepository.findOne(entityId);
        checkEntity(orderEntity);
        return orderExamConversionService.convert(orderEntity, Order.class);
    }

    @Override
    public Order create(Order request) {
        validate(request);
        OrderEntity orderEntity = orderExamConversionService.convert(request, OrderEntity.class);
        try {
            Set<OrderItemEntity> orderItemEntitySet = Sets.newHashSet();

            for (OrderItem orderItem : request.getItems()) {
                ItemEntity itemEntity = itemService.getEntity(orderItem.getId());
                if (itemEntity == null) {
                    throw new NotFoundException("404", "Item with id of '" + orderItem.getId() + "' cannot be found.");
                }
                OrderItemEntity orderItemEntity = orderItemPojoToOrderItemEntityConverter.convert(orderItem);
                orderItemEntity.setOrder(orderEntity);
                orderItemEntity.setCost(itemEntity.getCost());
                orderItemEntity.setCount(orderItem.getCount());
                orderItemEntitySet.add(orderItemEntity);
            }

            orderEntity = orderRepository.save(orderEntity);
            orderEntity.setOrderItems(Sets.newHashSet());

            Iterable<OrderItemEntity> orderItemEntityIterable = orderItemRepository.save(orderItemEntitySet);
            orderEntity.setOrderItems(Sets.newHashSet(orderItemEntityIterable));
            orderEntity = orderRepository.save(orderEntity);

        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            if (ex.getMostSpecificCause().toString().contains("ConstraintViolation")) {
                throw new ApiException("400", ex.getMostSpecificCause().toString());
            } else {
                throw new ApiException("400", "Invalid Data");
            }
        }
        return orderExamConversionService.convert(orderEntity, Order.class);
    }

    @Override
    public Order update(Long entityId, Order request) {
        OrderEntity entityDataEntity = orderRepository.findOne(entityId);
        checkEntity(entityDataEntity);
        Set<OrderItemEntity> orderItemEntityHashSet = Sets.newHashSet();
        Iterable<OrderItemEntity> mergedAlternativeNames = Stream.concat(Sets.newHashSet(orderItemEntityHashSet).stream(), entityDataEntity.getOrderItems().stream())
                .collect(Collectors.toSet());
        entityDataEntity.setOrderItems(Sets.newHashSet(mergedAlternativeNames));
        entityDataEntity.setPlacementDate(request.getPlacementDate());
        entityDataEntity.setCustomerName(request.getCustomerName());
        entityDataEntity = orderRepository.save(entityDataEntity);

        return orderExamConversionService.convert(entityDataEntity, Order.class);
    }

    @Override
    public void delete(Long entityId) {
        OrderEntity entityDataEntity = orderRepository.findOne(entityId);
        checkEntity(entityDataEntity);
        orderRepository.delete(entityId);
    }


    private void validate(Order request) {

    }

    private void checkEntity(OrderEntity entityDataEntity) {
        if (entityDataEntity == null) {
            throw new NotFoundException(NOT_FOUND_ERROR_STATUS, NOT_FOUND_ERROR_MESSAGE);
        }
    }
}
