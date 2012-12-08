package com.iangilham.properties;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
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
		assertEquals("failure - property not read", 1, props.size());
		assertEquals("failure - incorrect property value",
				"This is my value string.", props.get("key"));
	}
}
