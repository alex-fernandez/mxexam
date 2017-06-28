package com.alexfrndz.orderexam.controller;


import com.alexfrndz.orderexam.pojo.Order;
import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import com.alexfrndz.orderexam.service.OrderImpl;
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

@RequestMapping("/v1")
@RestController
@Slf4j
public class OrderAPIController extends BaseController {


    private OrderImpl orderService;

    @Autowired
    public OrderAPIController(OrderImpl orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "orders", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SearchResponse> search(
            @RequestParam(value = "customerName", required = false, defaultValue = "") String customerName,
            @ModelAttribute @Valid PaginationSearchRequest searchRequest) {
        return new ResponseEntity<SearchResponse>(orderService.search(customerName, searchRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "orders/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Order> get(@PathVariable Long orderId) {
        return new ResponseEntity<Order>(orderService.get(orderId), HttpStatus.OK);
    }

    @RequestMapping(value = "orders", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Order> create(@RequestBody @Valid Order request) {
        return new ResponseEntity<Order>(orderService.create(request), HttpStatus.CREATED);
    }

    @RequestMapping(value = "orders/{orderId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Order> update(@PathVariable Long orderId, @RequestBody @Valid Order request) {
        return new ResponseEntity<Order>(orderService.update(orderId, request), HttpStatus.OK);
    }

    @RequestMapping(value = "orders/{orderId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable Long orderId) {
        orderService.delete(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}