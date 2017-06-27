package com.alexfrndz.orderexam.service;


import com.alexfrndz.orderexam.config.SearchResponseConverter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;

@Setter
public abstract class AbstractService {

    @Autowired
    @Qualifier("orderExamConversionService")
    protected ConversionService orderExamConversionService;

    @Autowired
    protected SearchResponseConverter searchResponseConverter;



}
