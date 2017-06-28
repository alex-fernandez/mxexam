package com.alexfrndz.orderexam;

import com.alexfrndz.orderexam.controller.ItemAPIController;
import com.alexfrndz.orderexam.pojo.Item;
import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import com.alexfrndz.orderexam.service.ItemImpl;
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
@WebMvcTest(ItemAPIController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemImpl itemService;

    @Test
    public void testSearch() throws Exception {
        given(this.itemService.search(any(), any(PaginationSearchRequest.class)))
                .willReturn(new SearchResponse());

        this.mvc.perform(get("/v1/items").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void testGetItem() throws Exception {
        given(this.itemService.get(any())).willReturn(new Item());
        this.mvc.perform(get("/v1/items" + 1).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void testCreateItem() throws Exception {
        given(this.itemService.create(any())).willReturn(new Item());
        this.mvc.perform(post("/v1/items").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }


    @Test
    @Ignore
    public void tesUpdateItem() throws Exception {
        given(this.itemService.create(any())).willReturn(new Item());
        this.mvc.perform(put("/v1/items" + 1).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }


}
