package com.alexfrndz.orderexam;

import com.alexfrndz.orderexam.controller.OrderAPIController;
import com.alexfrndz.orderexam.pojo.Order;
import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import com.alexfrndz.orderexam.service.OrderImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderAPIController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderImpl orderService;

    @Test
    public void testSearch() throws Exception {
        given(this.orderService.search(any(), any(PaginationSearchRequest.class)))
                .willReturn(new SearchResponse());

        this.mvc.perform(get("/v1/orders").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void testGetOrder() throws Exception {
        given(this.orderService.get(any())).willReturn(new Order());
        this.mvc.perform(get("/v1/orders" + 1).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void testCreateOrder() throws Exception {
        given(this.orderService.create(any())).willReturn(new Order());
        this.mvc.perform(post("/v1/orders").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }


    @Test
    @Ignore
    public void tesUpdateOrder() throws Exception {
        given(this.orderService.create(any())).willReturn(new Order());
        this.mvc.perform(put("/v1/items" + 1).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }


}
