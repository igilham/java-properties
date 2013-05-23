package com.iangilham.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.Map;

/**
 * The Properties class represents a persistent set of properties in the
 * key=value format.
 * 
 * This implementation is intended to replace @link(java.util.Properties) in
 * most use cases. As such it implements the same basic functionality and fixes
 * various bugs and issues present in the native Java implementation.
 * 
 * Because Properties extends java.util.HashMap<String String>, it is safe to
 * use java.util.Map's put and putAll methods with this implementation.
 * 
 * One of the main weaknesses of standard Java Properties implementation is that
 * it defaults to ISO-8859-1 as its output encoding. This implementation
 * defaults to UTF-8, removing the burden of using Unicode escape characters for
 * common glyphs in many languages.
 * 
 * @see java.util.Properties
 */
public class Properties implements Serializable {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -1667669306471601205L;

	private final Map<String, String> map = new HashMap<String, String>();

	/**
	 * Name of the preferred character set.
	 */
	private static final String preferredCharset = "UTF-8";

	/**
	 * Default constructor.
	 */
	public Properties() {
	}

	/**
	 * Construct a new instance of Properties using the contents of an existing
	 * instance.
	 * 
	 * @param defaults
	 *            The pre-existing instance from which to take default values
	 *            during construction.
	 */
	public Properties(final Properties defaults) {
		this.map.putAll(defaults.map);
	}

	/**
	 * Load properties from an InputStream.
	 * 
	 * @param inStream
	 *            The InputStream from which to read properties.
	 */
	public void load(final InputStream inStream) throws IOException {
		InputStreamReader reader;
		try {
			if (Charset.isSupported(preferredCharset)) {
				reader = new InputStreamReader(inStream,
						Charset.forName(preferredCharset));
			} else {
				reader = new InputStreamReader(inStream,
						Charset.defaultCharset());
			}
			load(reader);
		} catch (final IllegalCharsetNameException e) {
			throw new IOException(e);
		} catch (final UnsupportedCharsetException e) {
			throw new IOException(e);
		} catch (final UnsupportedOperationException e) {
			throw new IOException(e);
		}
	}

	/**
	 * Load properties from a Reader.
	 * 
	 * @param reader
	 *            The Reader from which to read properties.
	 */
	public void load(final Reader reader) throws IOException {
		parseProperties(new BufferedReader(reader));
	}

	/**
	 * Store properties by writing to an OutputStream.
	 * 
	 * @param outStream
	 *            The stream to write to.
	 * @param comments
	 *            A comment string to prepend to the output.
	 */
	public void store(final OutputStream outStream, final String comments)
			throws IOException {
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Store properties by writing to a Writer.
	 * 
	 * @param writer
	 *            The stream to write to.
	 * @param comments
	 *            A comment string to prepend to the output.
	 */
	public void store(final Writer writer, final String comments)
			throws IOException {
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Gets the property associated with a key.
	 * 
	 * @param key
	 *            The key that references the desired property.
	 * @return The requested property's value if present, otherwise null.
	 */
	public String getProperty(final String key) {
		return map.get(key);
	}

	/**
	 * Add a property to the container. This will overwrite any existing
	 * property with the same key.
	 * 
	 * @param key
	 *            The key of the new property.
	 * @param value
	 *            The value of the new property.
	 */
	public void setProperty(final String key, final String value) {
		map.put(key, value);
	}

	/**
	 * Get the number of properties in the container.
	 * 
	 * @return The number of properties in the container.
	 */
	public int size() {
		return map.size();
	}

	/**
	 * Determine whether there are any properties in the container.
	 * 
	 * @return True if the container is empty, else false.
	 */
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * Parse lines from a properties source.
	 * 
	 * @param reader
	 *            The BufferedReader to use for reading lines from the
	 *            properties source.
	 * @throws IOException
	 *             If the reader throws.
	 */
	private void parseProperties(final BufferedReader reader)
			throws IOException {
		String property = "";
		String thisLine = reader.readLine();
		while (thisLine != null) {
			// ignore commented lines
			if (thisLine.startsWith("#")) {
				thisLine = reader.readLine();
				continue;
			}
			// allow the property to span multiple lines
			if (thisLine.endsWith("\\")) {
				thisLine = thisLine.substring(0, thisLine.length() - 1);
				property += thisLine.trim();
				thisLine = reader.readLine();
				continue;
			}
			property += thisLine.trim();

			// split the property into key and value
			int index = property.indexOf('=');
			// insert into the map
			if (index > 0) {
				map.put(property.substring(0, index),
						property.substring(index + 1));
			}

			// clean up loop side-effects
			thisLine = reader.readLine();
			property = "";
		}
	}
}
