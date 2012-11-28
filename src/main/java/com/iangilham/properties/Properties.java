package com.iangilham.properties;

import java.util.HashMap;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * The Properties class represents a persistent set of properties in the key=value format.
 * 
 * This implementation is intended to replace @link(java.util.Properties) in most use cases.
 * As such it implements the same basic functionality and fixes various bugs and
 * issues present in the native Java implementation.
 * 
 * Because Properties extends java.util.HashMap<String String>, it is safe to use 
 * java.util.Map's put and putAll methods with this implementation.
 * 
 * One of the main weaknesses of standard Java Properties implementation is that it 
 * defaults to ISO-8859-1 as its output encoding. This implementation defaults to UTF-8,
 * removing the burden of using unicode escape characters for common glyphs in many languages.
 * 
 * @see	java.util.Properties
 */
public class Properties extends HashMap<String, String> {
	
	/**
	 * Default constructor.
	 */
	public Properties() {
		super();
	}
	
	/**
	 * Construct a new instance of Properties using the contents of an existing instance.
	 * @param defaults The pre-existing instance from which to take default values during
	 * construction.
	 */
	public Properties(Properties defaults) {
		super(defaults);
	}
	
	/**
	 * Load properties from an InputStream.
	 * @param	inStream	The InputStream from which to read properties.
	 */
	public void load(InputStream inStream) throws IOException {
	}
	
	/**
	 * Load properties from a Reader.
	 * @param	reader	The Reader from which to read properties.
	 */
	public void load(Reader reader) throws IOException {
	}
	
	/**
	 * Store properties by writing to an OutputStream.
	 * @param	outStream	The stream to write to.
	 * @param	comments	A comment string to prepend to the output.
	 */
	public void store(OutputStream outStream, String comments) throws IOException {
	}
	
	/**
	 * Store properties by writing to a Writer.
	 * @param	writer	The stream to write to.
	 * @param	comments	A comment string to prepend to the output.
	 */
	public void store(Writer writer, String comments) throws IOException {
	}
}
