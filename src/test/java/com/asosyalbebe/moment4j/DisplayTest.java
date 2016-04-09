package com.asosyalbebe.moment4j;

import static com.asosyalbebe.moment4j.Moment.moment;

import org.junit.Assert;
import org.junit.Test;

public class DisplayTest {

    @Test
    public void test_format() {
	Moment moment = moment().years(2038).months(3).dates(21).hours(22).minutes(12).seconds(32).milliseconds(321);

	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String expectedResult = "2038-04-21 22:12:32.321";

	Assert.assertEquals(expectedResult, moment.format(dateFormat));
    }
}
