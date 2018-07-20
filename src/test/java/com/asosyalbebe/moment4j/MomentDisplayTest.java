package com.asosyalbebe.moment4j;

import com.asosyalbebe.moment4j.fault.MomentException;
import org.junit.Test;

import static com.asosyalbebe.moment4j.Moment.moment;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class MomentDisplayTest {

    @Test
    public void test_format() {
	Moment moment = moment().years(2038).months(3).dates(21).hours(22).minutes(12).seconds(32).milliseconds(321);

	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String expectedResult = "2038-04-21 22:12:32.321";

	assertEquals(expectedResult, moment.format(dateFormat));
    }

    @Test
    public void test_format_shouldThrowMomentExceptionWhenInvalidFormatIsProvided() {
	Moment moment = moment().years(2038).months(3).dates(21).hours(22).minutes(12).seconds(32).milliseconds(321);

	String dateFormat = "invalidformat";

	try {
	    moment.format(dateFormat);
	    fail("Should not format correctly!");
	} catch (MomentException e) {
	    assertNotNull(e.getMessage());
	    assertNotNull(e.getCause());
	}
    }

    @Test
    public void test_toString_shouldConvertToStringBeautifully() {
	Moment moment = moment().years(2038).months(3).dates(21).hours(22).minutes(12).seconds(32).milliseconds(321);

	String expectedResult = "Moment{ 2038/04/21 22:12:32.321 }";

	assertEquals(expectedResult, moment.toString());
    }

    @Test
    public void test_toString_shouldConvertToStringBeautifullyWithPrependingZeros() {
	Moment moment = moment().years(2038).months(3).dates(3).hours(1).minutes(2).seconds(5).milliseconds(98);

	String expectedResult = "Moment{ 2038/04/03 01:02:05.098 }";

	assertEquals(expectedResult, moment.toString());
    }
}
