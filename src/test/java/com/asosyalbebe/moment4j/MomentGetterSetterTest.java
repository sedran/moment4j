package com.asosyalbebe.moment4j;

import static com.asosyalbebe.moment4j.Moment.moment;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class MomentGetterSetterTest {

    @Test
    public void test_MillisecondSetterGetter() {
	Moment moment = moment();
	Moment returned = moment.milliseconds(12);

	assertSame(moment, returned);
	assertEquals(12, returned.milliseconds());
    }

    @Test
    public void test_SecondSetterGetter() {
	Moment moment = moment();
	Moment returned = moment.seconds(43);

	assertSame(moment, returned);
	assertEquals(43, returned.seconds());
    }

    @Test
    public void test_MinuteSetterGetter() {
	Moment moment = moment();
	Moment returned = moment.minutes(43);

	assertSame(moment, returned);
	assertEquals(43, returned.minutes());
    }

    @Test
    public void test_HourSetterGetter() {
	Moment moment = moment();
	Moment returned = moment.hours(11);

	assertSame(moment, returned);
	assertEquals(11, returned.hours());
    }

    @Test
    public void test_DayOfMonthSetterGetter() {
	Moment moment = moment();
	Moment returned = moment.dates(24);

	assertSame(moment, returned);
	assertEquals(24, returned.dates());
    }

    @Test
    public void test_DayOfWeekSetterGetter() {
	Moment moment = moment();

	for (int i = 1; i <= 7; i++) {
	    Moment returned = moment.days(i);
	    assertSame(moment, returned);
	    assertEquals(i, returned.days());
	}
    }

    @Test
    public void test_MonthSetterGetter() {
	Moment moment = moment();
	Moment returned = moment.months(9);

	assertSame(moment, returned);
	assertEquals(9, returned.months());
    }

    @Test
    public void test_YearSetterGetter() {
	Moment moment = moment();
	Moment returned = moment.years(2014);

	assertSame(moment, returned);
	assertEquals(2014, returned.years());
    }

    @Test
    public void test_DayOfYearSetterGetter() {
	Moment moment = moment();
	Moment returned = moment.dayOfYear(312);

	assertSame(moment, returned);
	assertEquals(312, returned.dayOfYear());
    }
}
