package com.alexfrndz.orderexam.controller;


import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.Item;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import com.alexfrndz.orderexam.service.ItemImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/")
@RestController
@Slf4j
public class ItemAPIController extends BaseController {


    private ItemImpl itemService;

    @Autowired
    public ItemAPIController(ItemImpl itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "items", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SearchResponse> search(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @ModelAttribute @Valid PaginationSearchRequest searchRequest) {
        return new ResponseEntity<SearchResponse>(itemService.search(name, searchRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "items/{itemId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Item> get(@PathVariable Long itemId) {
        return new ResponseEntity<Item>(itemService.get(itemId), HttpStatus.OK);
    }

    @RequestMapping(value = "items", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Item> create(@RequestBody @Valid Item request) {
        return new ResponseEntity<Item>(itemService.create(request), HttpStatus.CREATED);
    }

    @RequestMapping(value = "items/{itemId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Item> update(@PathVariable Long itemId, @RequestBody @Valid Item request) {
        return new ResponseEntity<Item>(itemService.update(itemId, request), HttpStatus.OK);
    }

    @RequestMapping(value = "items/{itemId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable Long itemId) {
        itemService.delete(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}