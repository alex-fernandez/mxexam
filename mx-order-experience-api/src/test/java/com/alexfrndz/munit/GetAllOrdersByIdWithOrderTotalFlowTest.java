
package com.alexfrndz.munit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.transport.PropertyScope;
import org.skyscreamer.jsonassert.JSONAssert;

import com.alexfrndz.munit.AbstractTestBase;;

public class GetAllOrdersByIdWithOrderTotalFlowTest extends AbstractTestBase {

    @Override
    protected String getConfigResources() {
        StringBuilder sb = new StringBuilder();
        sb.append("get-orders-by-id-compute-total.xml");
        return super.getConfigResources() + sb.toString();
    }


    @Test
    public void testGetOrderByIdWithTotalCompute_Success() throws Exception {

    	// Given
        String flowToTest = "get-orders-by-id-compute-total";
        
        mockProcessor("request","http", "Get Order by id Compute total").thenReturn(muleMessageWithPayload(TestUtils.getJsonPayloadFromFile("GetOrderByIdRequestWithItemsWithNonZeroTotal.json")));
        
        MuleMessage message = muleMessageWithPayload("");
        message.setProperty("http.method", "GET", PropertyScope.INBOUND);
        message.setProperty("http.status", "200", PropertyScope.INBOUND);
        message.setProperty("content-type", "application/json;charset=UTF-8", PropertyScope.INBOUND);
        
        // When
        MuleEvent test = testEvent(message);
        MuleEvent resultMuleEvent = runFlow(flowToTest, test);
        
        // Then
        assertThat("Flow should have a non null 'value'", resultMuleEvent.getMessage().getPayload(), Is.is(notNullValue()));
        assertThat("'http.status' should be '200'", resultMuleEvent.getMessage().getProperty("http.status", PropertyScope.OUTBOUND).toString(),Is.is("200"));
        assertThat("'content-type' should be 'application/json;charset=UTF-8'", resultMuleEvent.getMessage().getProperty("content-type",
        		PropertyScope.OUTBOUND).toString(), Is.is("application/json;charset=UTF-8"));
        verifyProcessorCall("request","http", "Get Order by id Compute total").times(1);
        String computedOrder = resultMuleEvent.getMessage().getPayload().toString();
        JSONAssert.assertEquals(TestUtils.getJsonPayloadFromFile("GetOrderByIdResponseSuccess.json"), computedOrder, false);
    }



    @Test
    public void testGetOrderByIdWithEmptyItemsWithZeroTotol_Success() throws Exception {

    	// Given
        String flowToTest = "get-orders-by-id-compute-total";
        
        mockProcessor("request","http", "Get Order by id Compute total").thenReturn(muleMessageWithPayload(TestUtils.getJsonPayloadFromFile("GetOrderByIdRequestWithEmptyItems.json")));
        
        MuleMessage message = muleMessageWithPayload("");
        message.setProperty("http.method", "GET", PropertyScope.INBOUND);
        message.setProperty("http.status", "200", PropertyScope.INBOUND);
        message.setProperty("content-type", "application/json;charset=UTF-8", PropertyScope.INBOUND);
           	
        // When 
        MuleEvent test = testEvent(message);
        MuleEvent resultMuleEvent = runFlow(flowToTest, test);
        
        // Then
        assertThat("Flow should have a non null 'value'", resultMuleEvent.getMessage().getPayload(), Is.is(notNullValue()));
        assertThat("'http.status' should be '200'", resultMuleEvent.getMessage().getProperty("http.status", PropertyScope.OUTBOUND).toString(),Is.is("200"));
        assertThat("'content-type' should be 'application/json;charset=UTF-8'", resultMuleEvent.getMessage().getProperty("content-type",
        		PropertyScope.OUTBOUND).toString(), Is.is("application/json;charset=UTF-8"));
        verifyProcessorCall("request","http", "Get Order by id Compute total").times(1);
        String computedOrder = resultMuleEvent.getMessage().getPayload().toString();
        JSONAssert.assertEquals(TestUtils.getJsonPayloadFromFile("GetOrderByIdResponseSuccessWithEmptyItemsAndTotalIsZero.json"), computedOrder, false);
    }
    

    
}
