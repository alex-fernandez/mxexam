package com.alexfrndz.munit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mule.munit.common.mocking.Attribute;
import org.mule.munit.common.mocking.MessageProcessorMocker;
import org.mule.munit.common.mocking.MunitVerifier;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public abstract class AbstractTestBase extends FunctionalMunitSuite {

	@BeforeClass
	public static void setEnvironmentVariables() {
		System.getProperties().put("env", "test");		
		FileUtils.deleteQuietly(new File(".mule"));
	}
	

	@AfterClass
	public static void afterClass() {
		FileUtils.deleteQuietly(new File(".mule"));
	}
	
	
	@Override
	protected String getConfigResources() {
	  StringBuilder sb = new StringBuilder();
	  sb.append("mule-config.xml");
	  sb.append(",");
	  return sb.toString();
	}
	

	

	
	protected static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected boolean haveToDisableInboundEndpoints() {
		return false;
	}

	@Override
	protected boolean haveToMockMuleConnectors() {
		return false;
	}

	protected String loadFileContent(String fileName) {
		try {
			return IOUtils.toString(getClass().getResourceAsStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@SuppressWarnings("unused")
	private static String toPath(String resourcePath) {
		try {
			return new File(Resources.getResource(resourcePath).toURI()).getCanonicalPath();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	protected static Map<String, Object> createMapFromJson(String jsonRequests) {
		TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
		};
		try {
			return objectMapper.readValue(jsonRequests, typeRef);
		} catch (Exception ex) {
			return null;
		}
	}
   

	protected MunitVerifier verifyProcessorCall(String processor, String namespace, String name) {
		return verifyCallOfMessageProcessor(processor).ofNamespace(namespace)
				.withAttributes(Attribute.attribute("name").ofNamespace("doc")
						.withValue(name));
	}

	protected MunitVerifier verifyProcessorCall(String processor, String name) {
		return verifyCallOfMessageProcessor(processor)
				.withAttributes(Attribute.attribute("name").ofNamespace("doc")
						.withValue(name));
	}

	protected MessageProcessorMocker mockProcessor(String processor, String namespace, String name) {
		return whenMessageProcessor(processor).ofNamespace(namespace)
				.withAttributes(Attribute.attribute("name").ofNamespace("doc")
						.withValue(name));
	}
	

	protected MessageProcessorMocker mockProcessor(String processor, String name) {
		return whenMessageProcessor(processor)
				.withAttributes(Attribute.attribute("name").ofNamespace("doc")
						.withValue(name));
	}
	

}