package com.iangilham.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class PropertiesTest {
	@Test
	public void loadBasic() throws IOException {
		final Properties props = new Properties();
		try {
			InputStream is = getClass()
					.getResourceAsStream("/basic.properties");
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertFalse("failure - properties is empty", props.isEmpty());
		assertEquals("failure - property not read", 1, props.size());
		assertEquals("failure - incorrect property value",
				"This is my value string.", props.getProperty("key"));
	}
}
