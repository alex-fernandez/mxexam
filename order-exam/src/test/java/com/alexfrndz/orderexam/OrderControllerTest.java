package com.alexfrndz.orderexam;

import com.alexfrndz.orderexam.controller.OrderAPIController;
import com.alexfrndz.orderexam.pojo.Order;
import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import com.alexfrndz.orderexam.service.OrderImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSearch() throws Exception {

        SearchResponse searchResponse = objectMapper.readValue(TestUtils.getJsonPayloadFromFile("OrderSearchResponse.json"), SearchResponse.class);

        given(this.orderService.search(any(), any(PaginationSearchRequest.class)))
                .willReturn(searchResponse);

        MvcResult mvcResult = this.mvc.perform(get("/v1/orders").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String apiResponse = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(TestUtils.getJsonPayloadFromFile("OrderSearchResponse.json"), apiResponse, false);

    }

    @Test
    public void testGetOrder() throws Exception {
        Order order = objectMapper.readValue(TestUtils.getJsonPayloadFromFile("OrderGetResponse.json"), Order.class);
        given(this.orderService.get(any())).willReturn(order);
        MvcResult mvcResult = this.mvc.perform(get("/v1/orders/" + 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String apiResponse = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(TestUtils.getJsonPayloadFromFile("OrderGetResponse.json"), apiResponse, false);

    }

    @Test
    public void testCreateOrder() throws Exception {
        Order orderResponse = objectMapper.readValue(TestUtils.getJsonPayloadFromFile("OrderGetResponse.json"), Order.class);
        given(this.orderService.create(any())).willReturn(orderResponse);

        MvcResult mvcResult = this.mvc.perform(post("/v1/orders", orderResponse)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.getJsonPayloadFromFile("OrderCreateRequest.json"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        String apiResponse = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(TestUtils.getJsonPayloadFromFile("OrderGetResponse.json"), apiResponse, false);
    }


    @Test
    public void tesUpdateOrder() throws Exception {
        Order orderResponse = objectMapper.readValue(TestUtils.getJsonPayloadFromFile("OrderGetResponse.json"), Order.class);

        System.out.println("OrderGetResponse " + TestUtils.getJsonPayloadFromFile("OrderGetResponse.json"));

        given(this.orderService.update(any(), any())).willReturn(orderResponse);

        MvcResult mvcResult = this.mvc.perform(put("/v1/orders/" + 1, orderResponse)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.getJsonPayloadFromFile("OrderUpdateRequest.json"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String apiResponse = mvcResult.getResponse().getContentAsString();

        System.out.println("apiResponse " + apiResponse);
        JSONAssert.assertEquals(TestUtils.getJsonPayloadFromFile("OrderGetResponse.json"), apiResponse, false);

    }


}
