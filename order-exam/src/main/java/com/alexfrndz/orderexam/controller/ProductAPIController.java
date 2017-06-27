package com.alexfrndz.orderexam.controller;


import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.Product;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import com.alexfrndz.orderexam.service.ProductImpl;
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
public class ProductAPIController extends BaseController {


    private ProductImpl productService;

    @Autowired
    public ProductAPIController(ProductImpl productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "products", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SearchResponse> search(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @ModelAttribute @Valid PaginationSearchRequest searchRequest) {
        return new ResponseEntity<SearchResponse>(productService.search(name, searchRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "products/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Product> get(@PathVariable Long productId) {
        return new ResponseEntity<Product>(productService.get(productId), HttpStatus.OK);
    }

    @RequestMapping(value = "products", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Product> create(@RequestBody @Valid Product request) {
        return new ResponseEntity<Product>(productService.create(request), HttpStatus.CREATED);
    }

    @RequestMapping(value = "products/{productId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Product> update(@PathVariable Long productId, @RequestBody @Valid Product request) {
        return new ResponseEntity<Product>(productService.update(productId, request), HttpStatus.OK);
    }

    @RequestMapping(value = "products/{productId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable Long productId) {
        productService.delete(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}