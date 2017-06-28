package com.alexfrndz.orderexam;

import org.apache.commons.io.IOUtils;
import java.io.FileInputStream;
import java.io.IOException;

public class TestUtils {

	 public static String getJsonPayloadFromFile(String file) throws IOException {
		String payload = null;
		FileInputStream inputStream = new FileInputStream("./src/test/resources/sample-data/"+file);
		try {
			payload = IOUtils.toString(inputStream);
		} finally {
			inputStream.close();
		}
		return payload;
	}
	

	
	
}