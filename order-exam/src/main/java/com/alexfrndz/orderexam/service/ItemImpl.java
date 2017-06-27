package com.alexfrndz.orderexam.service;

import com.alexfrndz.orderexam.entity.ItemEntity;
import com.alexfrndz.orderexam.pojo.Item;
import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import com.alexfrndz.orderexam.pojo.exception.NotFoundException;
import com.alexfrndz.orderexam.repository.ItemRepository;
import com.alexfrndz.orderexam.utils.ConverterBasedTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemImpl extends AbstractService implements IProduct {

    private ItemRepository itemRepository;
    private ConverterBasedTransformer<ItemEntity, Item> itemEntityItemConverterBasedTransformer;

    private static final String NOT_FOUND_ERROR_STATUS = "Not Found";
    private static final String NOT_FOUND_ERROR_MESSAGE = "No Item Found";

    @Autowired
    public ItemImpl(
            ItemRepository itemRepository,
            @Qualifier("itemEntityItemConverterBasedTransformer") ConverterBasedTransformer<ItemEntity, Item> itemEntityItemConverterBasedTransformer
    ) {
        this.itemRepository = itemRepository;
        this.itemEntityItemConverterBasedTransformer = itemEntityItemConverterBasedTransformer;
    }

    @Override
    public SearchResponse search(String name, PaginationSearchRequest searchRequest) {
        SearchResponse<ItemEntity> searchResponse = itemRepository.search(name, searchRequest);
        return searchResponseConverter.buildSearchResponse(searchResponse, itemEntityItemConverterBasedTransformer);
    }

    @Override
    public Item get(Long itemId) {
        ItemEntity itemEntity = itemRepository.findOne(itemId);
        checkEntity(itemEntity);
        return orderExamConversionService.convert(itemEntity, Item.class);
    }

    @Override
    public Item create(Item request) {
        return null;
    }

    @Override
    public Item update(Long itemId, Item request) {
        return null;
    }

    @Override
    public void delete(Long itemId) {

    }


    private void checkEntity(ItemEntity entityDataEntity) {
        if (entityDataEntity == null) {
            throw new NotFoundException(NOT_FOUND_ERROR_STATUS, NOT_FOUND_ERROR_MESSAGE);
        }
    }
}
