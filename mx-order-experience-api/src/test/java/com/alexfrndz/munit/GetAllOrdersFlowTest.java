
package com.alexfrndz.munit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.transport.PropertyScope;
import com.alexfrndz.munit.AbstractTestBase;;

public class GetAllOrdersFlowTest extends AbstractTestBase {

    @Override
    protected String getConfigResources() {
        StringBuilder sb = new StringBuilder();
        sb.append("get-orders.xml");
        return super.getConfigResources() + sb.toString();
    }


    @Test
    public void testGetAllOrders_Success() throws Exception {

    	// Given
        String flowToTest = "get-orders-sub-flow";
        mockProcessor("request","http", "Get Orders").thenReturn(muleMessageWithPayload(TestUtils.getJsonPayloadFromFile("GetOrdersResponseSuccess.json")));
        
        MuleMessage message = muleMessageWithPayload("");
        message.setProperty("http.method", "GET", PropertyScope.INBOUND);
        message.setProperty("http.status", "200", PropertyScope.INBOUND);
        message.setProperty("content-type", "application/json;charset=UTF-8", PropertyScope.INBOUND);
           		
        // When
        MuleEvent test = testEvent(message);
        MuleEvent resultMuleEvent = runFlow(flowToTest, test);
        
        
        // Then
       assertThat("Flow should have a non null 'value'", resultMuleEvent.getMessage().getPayload(), Is.is(notNullValue()));
       verifyProcessorCall("request","http", "Get Orders").times(1);

        assertThat("'http.status' should be '200'", resultMuleEvent.getMessage().getProperty("http.status", PropertyScope.OUTBOUND).toString(),Is.is("200"));
        assertThat("'content-type' should be 'application/json;charset=UTF-8'", resultMuleEvent.getMessage().getProperty("content-type",
        		PropertyScope.OUTBOUND).toString(), Is.is("application/json;charset=UTF-8"));
    }


    
    @Test
    public void testGetAllOrders_ServerBadRequest() throws Exception {

    	// Given
        String flowToTest = "get-orders-sub-flow";
        mockProcessor("request","http", "Get Orders").thenReturn(muleMessageWithPayload(TestUtils.getJsonPayloadFromFile("GetOrdersResponseBadRequest.json")));
        
        MuleMessage message = muleMessageWithPayload("");
        message.setProperty("http.method", "GET", PropertyScope.INBOUND);
        message.setProperty("http.status", "400", PropertyScope.INBOUND);
        message.setProperty("content-type", "application/json;charset=UTF-8", PropertyScope.INBOUND);
       
        // When
        MuleEvent test = testEvent(message);
        MuleEvent resultMuleEvent = runFlow(flowToTest, test);
        
        // Then
        assertThat("Flow should have a non null 'value'", resultMuleEvent.getMessage().getPayload(), Is.is(notNullValue()));
        assertThat("'http.status' should be '200'", resultMuleEvent.getMessage().getProperty("http.status", PropertyScope.OUTBOUND).toString(),Is.is("400"));
        assertThat("'content-type' should be 'application/json;charset=UTF-8'", resultMuleEvent.getMessage().getProperty("content-type",
        		PropertyScope.OUTBOUND).toString(), Is.is("application/json;charset=UTF-8"));
        verifyProcessorCall("request","http", "Get Orders").times(1);
    }
    
    

    @Test
    public void testGetAllOrders_ServerError() throws Exception {

        String flowToTest = "get-orders-sub-flow";
        
        mockProcessor("request","http", "Get Orders").thenReturn(muleMessageWithPayload(TestUtils.getJsonPayloadFromFile("GetOrdersResponseBadRequest.json")));
        
        MuleMessage message = muleMessageWithPayload("");
        message.setProperty("http.method", "GET", PropertyScope.INBOUND);
        message.setProperty("http.status", "500", PropertyScope.INBOUND);
        message.setProperty("content-type", "application/json;charset=UTF-8", PropertyScope.INBOUND);
           		
        MuleEvent test = testEvent(message);
        MuleEvent resultMuleEvent = runFlow(flowToTest, test);
        
        
       assertThat("Flow should have a non null 'value'", resultMuleEvent.getMessage().getPayload(), Is.is(notNullValue()));
       verifyProcessorCall("request","http", "Get Orders").times(1);

        assertThat("'http.status' should be '500'", resultMuleEvent.getMessage().getProperty("http.status", PropertyScope.OUTBOUND).toString(),Is.is("500"));
        assertThat("'content-type' should be 'application/json;charset=UTF-8'", resultMuleEvent.getMessage().getProperty("content-type",
        		PropertyScope.OUTBOUND).toString(), Is.is("application/json;charset=UTF-8"));
    }
    
    
    
    
}
