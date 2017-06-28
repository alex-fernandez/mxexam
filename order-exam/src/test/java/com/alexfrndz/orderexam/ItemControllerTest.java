package com.alexfrndz.orderexam;

import com.alexfrndz.orderexam.controller.ItemAPIController;
import com.alexfrndz.orderexam.pojo.Item;
import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import com.alexfrndz.orderexam.service.ItemImpl;
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
@WebMvcTest(ItemAPIController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemImpl itemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSearch() throws Exception {

        SearchResponse searchResponse = objectMapper.readValue(TestUtils.getJsonPayloadFromFile("controller/ItemSearchResponse.json"), SearchResponse.class);

        given(this.itemService.search(any(), any(PaginationSearchRequest.class)))
                .willReturn(searchResponse);

        MvcResult mvcResult = this.mvc.perform(get("/v1/items").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String apiResponse = mvcResult.getResponse().getContentAsString();

        JSONAssert.assertEquals(TestUtils.getJsonPayloadFromFile("controller/ItemSearchResponse.json"), apiResponse, false);

    }

    @Test
    public void testGetItem() throws Exception {
        Item item = objectMapper.readValue(TestUtils.getJsonPayloadFromFile("controller/ItemGetResponse.json"), Item.class);
        given(this.itemService.get(any())).willReturn(item);
        this.mvc.perform(get("/v1/items/" + 1).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testCreateItem() throws Exception {
        Item itemRequest = objectMapper.readValue(TestUtils.getJsonPayloadFromFile("controller/ItemCreateRequest.json"), Item.class);
        Item itemResponse = objectMapper.readValue(TestUtils.getJsonPayloadFromFile("controller/ItemGetResponse.json"), Item.class);
        given(this.itemService.create(any())).willReturn(itemResponse);

        MvcResult mvcResult = this.mvc.perform(post("/v1/items", itemRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.getJsonPayloadFromFile("controller/ItemCreateRequest.json"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        String apiResponse = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(TestUtils.getJsonPayloadFromFile("controller/ItemGetResponse.json"), apiResponse, false);

    }


    @Test
    public void tesUpdateItem() throws Exception {
        Item itemRequest = objectMapper.readValue(TestUtils.getJsonPayloadFromFile("controller/ItemCreateRequest.json"), Item.class);
        Item itemResponse = objectMapper.readValue(TestUtils.getJsonPayloadFromFile("controller/ItemGetResponse.json"), Item.class);

        given(this.itemService.update(any(), any())).willReturn(itemResponse);
        MvcResult mvcResult = this.mvc.perform(put("/v1/items/" + 1, itemRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.getJsonPayloadFromFile("controller/ItemCreateRequest.json"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String apiResponse = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(TestUtils.getJsonPayloadFromFile("controller/ItemGetResponse.json"), apiResponse, false);
    }


}
