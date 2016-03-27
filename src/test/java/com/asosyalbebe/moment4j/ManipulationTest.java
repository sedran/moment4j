package com.asosyalbebe.moment4j;

import static com.asosyalbebe.moment4j.Moment.moment;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

public class ManipulationTest {

    @Test
    public void test_add() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.add(1, Calendar.DATE);

	date = "2016-03-16 23:36:12.532";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.get(Calendar.DATE), moment.get(Calendar.DATE));
    }

    @Test
    public void test_subtract() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.subtract(1, Calendar.YEAR);

	date = "2015-03-15 23:36:12.532";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.get(Calendar.YEAR), moment.get(Calendar.YEAR));
    }

    @Test
    public void test_set_day_of_year() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.set(Calendar.DAY_OF_YEAR, 5);

	date = "2015-01-05 23:36:12.532";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.get(Calendar.DAY_OF_YEAR), moment.get(Calendar.DAY_OF_YEAR));
    }

    @Test
    public void test_startOf_YEAR() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.YEAR);

	date = "2016-01-01 00:00:00.00";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_startOf_MONTH() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.MONTH);

	date = "2016-03-01 00:00:00.00";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_startOf_DAY_OF_MONTH() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.DAY_OF_MONTH);

	date = "2016-03-15 00:00:00.00";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_startOf_DAY_OF_WEEK() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.DAY_OF_WEEK);

	date = "2016-03-15 00:00:00.00";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_startOf_DAY_OF_WEEK_IN_MONTH() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.DAY_OF_WEEK_IN_MONTH);

	date = "2016-03-15 00:00:00.00";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_startOf_WEEK_OF_MONTH() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-27 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.WEEK_OF_MONTH);

	date = "2016-03-27 00:00:00.00";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_startOf_WEEK_OF_YEAR() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-27 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.WEEK_OF_YEAR);

	date = "2016-03-27 00:00:00.00";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_startOf_DAY_OF_YEAR() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.DAY_OF_YEAR);

	date = "2016-03-15 00:00:00.00";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_startOf_HOUR() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.HOUR);

	date = "2016-03-15 23:00:00.00";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_startOf_HOUR_OF_DAY() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.HOUR_OF_DAY);

	date = "2016-03-15 23:00:00.00";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_startOf_MINUTE() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.MINUTE);

	date = "2016-03-15 23:36:00.00";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_startOf_SECOND() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.SECOND);

	date = "2016-03-15 23:36:12.00";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_startOf_MILLISECOND() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.MILLISECOND);

	date = "2016-03-15 23:36:12.532";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    // End of

    @Test
    public void test_endOf_YEAR() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.endOf(Calendar.YEAR);

	date = "2016-12-31 23:59:59.999";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_endOf_MONTH() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.startOf(Calendar.MONTH);

	date = "2016-03-01 00:00:00.00";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_endOf_DAY_OF_MONTH() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.endOf(Calendar.DAY_OF_MONTH);

	date = "2016-03-15 23:59:59.999";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_endOf_DAY_OF_WEEK() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.endOf(Calendar.DAY_OF_WEEK);

	date = "2016-03-15 23:59:59.999";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_endOf_DAY_OF_WEEK_IN_MONTH() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.endOf(Calendar.DAY_OF_WEEK_IN_MONTH);

	date = "2016-03-21 23:59:59.999";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_endOf_DAY_OF_YEAR() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.endOf(Calendar.DAY_OF_YEAR);

	date = "2016-03-15 23:59:59.999";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_endOf_HOUR_OF_DAY() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.endOf(Calendar.HOUR_OF_DAY);

	date = "2016-03-15 23:59:59.999";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_endOf_MINUTE() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.endOf(Calendar.MINUTE);

	date = "2016-03-15 23:36:59.999";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_endOf_SECOND() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.endOf(Calendar.SECOND);

	date = "2016-03-15 23:36:12.999";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }

    @Test
    public void test_endOf_MILLISECOND() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	moment.endOf(Calendar.MILLISECOND);

	date = "2016-03-15 23:36:12.532";
	Moment expected = moment(date, dateFormat);

	assertEquals(expected.valueOf(), moment.valueOf());
    }
}
