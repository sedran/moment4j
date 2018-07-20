package com.asosyalbebe.moment4j.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Serdar Kuzucu
 */
public class FormatUtilsTest {

    @Test
    public void toTwoDigitsString_shouldPadWithZeroWhenLessThan10() {
	assertEquals("00", FormatUtils.toTwoDigitsString(0));
	assertEquals("01", FormatUtils.toTwoDigitsString(1));
	assertEquals("05", FormatUtils.toTwoDigitsString(5));
	assertEquals("09", FormatUtils.toTwoDigitsString(9));
    }

    @Test
    public void toTwoDigitsString_shouldNotPadWhenGreaterThanOrEqualTo10() {
	assertEquals("10", FormatUtils.toTwoDigitsString(10));
	assertEquals("23", FormatUtils.toTwoDigitsString(23));
	assertEquals("99", FormatUtils.toTwoDigitsString(99));
	assertEquals("101", FormatUtils.toTwoDigitsString(101));
    }

    @Test
    public void toTwoDigitsString_shouldNotPadWhenLessThanZero() {
	assertEquals("-1", FormatUtils.toTwoDigitsString(-1));
	assertEquals("-11", FormatUtils.toTwoDigitsString(-11));
	assertEquals("-111", FormatUtils.toTwoDigitsString(-111));
    }

    @Test
    public void toThreeDigitsString_shouldPadWithOneZeroWhenLessThan100() {
	assertEquals("010", FormatUtils.toThreeDigitsString(10));
	assertEquals("011", FormatUtils.toThreeDigitsString(11));
	assertEquals("051", FormatUtils.toThreeDigitsString(51));
	assertEquals("091", FormatUtils.toThreeDigitsString(91));
    }

    @Test
    public void toThreeDigitsString_shouldPadWithTwoZerosWhenLessThan10() {
	assertEquals("000", FormatUtils.toThreeDigitsString(0));
	assertEquals("001", FormatUtils.toThreeDigitsString(1));
	assertEquals("003", FormatUtils.toThreeDigitsString(3));
	assertEquals("005", FormatUtils.toThreeDigitsString(5));
	assertEquals("009", FormatUtils.toThreeDigitsString(9));
    }

    @Test
    public void toThreeDigitsString_shouldNotPadWhenGreaterThanOrEqualTo100() {
	assertEquals("100", FormatUtils.toThreeDigitsString(100));
	assertEquals("230", FormatUtils.toThreeDigitsString(230));
	assertEquals("990", FormatUtils.toThreeDigitsString(990));
	assertEquals("1010", FormatUtils.toThreeDigitsString(1010));
    }

    @Test
    public void toThreeDigitsString_shouldNotPadWhenLessThanZero() {
	assertEquals("-1", FormatUtils.toThreeDigitsString(-1));
	assertEquals("-11", FormatUtils.toThreeDigitsString(-11));
	assertEquals("-111", FormatUtils.toThreeDigitsString(-111));
    }
}