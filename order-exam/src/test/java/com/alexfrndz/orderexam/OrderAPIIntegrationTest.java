package com.alexfrndz.orderexam;

import com.alexfrndz.orderexam.pojo.Item;
import com.alexfrndz.orderexam.pojo.Order;
import com.alexfrndz.orderexam.repository.OrderRepository;
import com.alexfrndz.orderexam.service.ItemImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderAPIIntegrationTest {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemImpl itemService;


    @LocalServerPort
    private int port;

    public String getURL(String path) {
        return "http://localhost:" + port + path;
    }

    @Test
    public void testSearch_Success_EmptySearchData() throws Exception {
        orderRepository.deleteAll();
        String apiResponse = this.restTemplate.getForObject(getURL("/v1/orders"), String.class);
        String expected = TestUtils.getJsonPayloadFromFile("it/ItemSearchResponse.json");
        assertThat("Order should have an empty search response", apiResponse, Is.is(expected));
    }


    @Test
    public void testCreateOrder__NoItemExistSuccess() throws Exception {
        ResponseEntity<Order> apiResponse = this.restTemplate.postForEntity(getURL("/v1/orders"), createEntity("it/OrderCreateRequest.json"), Order.class);
        assertThat("Order API http status should be '404'", apiResponse.getStatusCodeValue(), Is.is(404));
    }

    @Test
    public void testCreateOrder_ItemExistSuccess_shouldCreateOder() throws Exception {
        createItem();
        ResponseEntity<Order> apiResponse = this.restTemplate.postForEntity(getURL("/v1/orders"), createEntity("it/OrderCreateRequest.json"), Order.class);
        assertThat("Order API http status should be '201'", apiResponse.getStatusCodeValue(), Is.is(201));
        itemService.delete(1L);
    }


    private HttpEntity createEntity(String fileName) throws Exception {
        String jsonPayloadFromFile = TestUtils.getJsonPayloadFromFile(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(jsonPayloadFromFile, headers);
    }

    private void createItem() throws Exception {
        Item item = objectMapper.readValue(TestUtils.getJsonPayloadFromFile("it/ItemCreateRequest.json"), Item.class);
        itemService.create(item);
    }

}

