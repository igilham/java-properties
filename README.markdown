Java Properties
===============

A clean implementation of Java's java.util.properties, aiming to avoid the pitfalls of the
implementation build into the JRE.

Issues with java.util.Properties
--------------------------------

Java's Properties is not entirely type-safe because it allows the user to insert objects of
arbitrary type. This will cause errors when the user attempts to store the properties in a file.

The native implementation also assumes the output encoding is ISO-8859-1, making it difficult to
use the format for localization. This can be overridden by using the store(Writer) method, but it is
more useful to assume UTF-8 by default, as this allows most common characters in a single byte and
will not break when it encounters an unusual character.

The standard implementation has issues parsing some very common European punctuation, notably
falling over French apostrophes unless they are escaped by repeated duplication.
