package com.asosyalbebe.moment4j.util;

/**
 * Static helper methods to format date fields or instances
 *
 * @author Serdar Kuzucu
 */
public class FormatUtils {
    private FormatUtils() {
	// Prevent new instances of utility class
    }

    /**
     * Converts an integer value to string by padding with a single zero if the value is less than 10
     *
     * @param value integer to be converted to string with padding when necessary
     * @return string value of the given integer by padding with zero when necessary
     */
    public static String toTwoDigitsString(int value) {
	final String stringValue = String.valueOf(value);
	if (value < 10 && value >= 0) {
	    return "0".concat(stringValue);
	}
	return stringValue;
    }

    /**
     * Converts an integer value to string of length 3. Pads with zeros when necessary.
     *
     * @param value integer to be converted to string with padding when necessary
     * @return string value of the given integer by padding with zero when necessary
     */
    public static String toThreeDigitsString(int value) {
	final String stringValue = String.valueOf(value);
	if (value < 0) {
	    return stringValue;
	}

	if (value < 10) {
	    return "00".concat(stringValue);
	}

	if (value < 100) {
	    return "0".concat(stringValue);
	}
	return stringValue;
    }
}
