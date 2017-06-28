package com.alexfrndz.munit;

import org.apache.commons.io.IOUtils;
import java.io.FileInputStream;
import java.io.IOException;

public class TestUtils {

	static public String getJsonPayloadFromFile(String file) throws IOException {
		String payload = null;
		FileInputStream inputStream = new FileInputStream("./src/test/resources/test-data/json/"+file);
		try {
			payload = IOUtils.toString(inputStream);
		} finally {
			inputStream.close();
		}
		return payload;
	}
	
	static public String getXmlPayloadFromFile(String file) throws IOException {
		String payload = null;
		FileInputStream inputStream = new FileInputStream("./src/test/resources/test-data/xml/"+file);
		try {
			payload = IOUtils.toString(inputStream);
		} finally {
			inputStream.close();
		}
		return payload;
	}
	
	
	
}