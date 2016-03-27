package com.asosyalbebe.moment4j;

import static com.asosyalbebe.moment4j.Moment.moment;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class ComparisonTest {
    @Test
    public void test_max_null_param() {
	long beforeTime = moment().valueOf();
	Moment max = Moment.max((Moment[]) null);
	long afterTime = moment().valueOf();

	assertNotNull(max);

	assertThat(max.valueOf(), greaterThanOrEqualTo(beforeTime));
	assertThat(max.valueOf(), lessThanOrEqualTo(afterTime));
    }

    @Test
    public void test_max_empty_array() {
	long beforeTime = moment().valueOf();
	Moment max = Moment.max(new Moment[] {});
	long afterTime = moment().valueOf();

	assertNotNull(max);

	assertThat(max.valueOf(), greaterThanOrEqualTo(beforeTime));
	assertThat(max.valueOf(), lessThanOrEqualTo(afterTime));
    }

    @Test
    public void test_max_single_value() {
	Calendar ref = Calendar.getInstance();
	ref.add(Calendar.YEAR, 1);

	Moment max = Moment.max(new Moment[] { moment(ref) });

	assertNotNull(max);
	assertEquals(ref.getTimeInMillis(), max.valueOf());
    }

    @Test
    public void test_max_multiple_value() {
	Moment expectedMax = moment("2023", "yyyy");

	Moment[] momentsToCompare = new Moment[] { moment("2016", "yyyy"), moment("2017", "yyyy"), moment("2015", "yyyy"), expectedMax, moment("2014", "yyyy"),
		moment("2016", "yyyy"), moment("2018", "yyyy") };

	Moment max = Moment.max(momentsToCompare);

	assertNotNull(max);
	assertSame(expectedMax, max);
    }

    @Test
    public void test_min_null_param() {
	long beforeTime = moment().valueOf();
	Moment min = Moment.min((Moment[]) null);
	long afterTime = moment().valueOf();

	assertNotNull(min);

	assertThat(min.valueOf(), greaterThanOrEqualTo(beforeTime));
	assertThat(min.valueOf(), lessThanOrEqualTo(afterTime));
    }

    @Test
    public void test_min_empty_array() {
	long beforeTime = moment().valueOf();
	Moment min = Moment.min(new Moment[] {});
	long afterTime = moment().valueOf();

	assertNotNull(min);
	assertThat(min.valueOf(), greaterThanOrEqualTo(beforeTime));
	assertThat(min.valueOf(), lessThanOrEqualTo(afterTime));
    }

    @Test
    public void test_min_single_value() {
	Calendar ref = Calendar.getInstance();
	ref.add(Calendar.YEAR, 1);

	Moment min = Moment.min(new Moment[] { moment(ref) });

	assertNotNull(min);
	assertEquals(ref.getTimeInMillis(), min.valueOf());
    }

    @Test
    public void test_min_multiple_value() {
	Moment expectedMin = moment("2012", "yyyy");

	Moment[] momentsToCompare = new Moment[] { moment("2016", "yyyy"), moment("2017", "yyyy"), moment("2015", "yyyy"), expectedMin, moment("2014", "yyyy"),
		moment("2016", "yyyy"), moment("2018", "yyyy") };

	Moment min = Moment.min(momentsToCompare);

	assertNotNull(min);
	assertSame(expectedMin, min);
    }

    @Test
    public void test_isBefore_True_MomentParam() {
	Moment moment = moment(System.currentTimeMillis());
	Moment futureMoment = moment(System.currentTimeMillis() + 1000);

	assertTrue(moment.isBefore(futureMoment));
    }

    @Test
    public void test_isBefore_False_MomentParam_Same() {
	Moment moment = moment(System.currentTimeMillis());
	Moment sameMoment = moment(moment);

	assertFalse(moment.isBefore(sameMoment));
    }

    @Test
    public void test_isBefore_False_MomentParam_After() {
	Moment moment = moment(System.currentTimeMillis());
	Moment oldMoment = moment(moment.valueOf() - 1000);

	assertFalse(moment.isBefore(oldMoment));
    }

    @Test
    public void test_isBefore_True_DateParam() {
	Moment moment = moment(System.currentTimeMillis());
	Moment futureMoment = moment(System.currentTimeMillis() + 1000);
	Date futureDate = futureMoment.toDate();

	assertTrue(moment.isBefore(futureDate));
    }

    @Test
    public void test_isBefore_False_DateParam_Same() {
	Moment moment = moment(System.currentTimeMillis());
	Moment sameMoment = moment(moment);
	Date sameDate = sameMoment.toDate();

	assertFalse(moment.isBefore(sameDate));
    }

    @Test
    public void test_isBefore_False_DateParam_After() {
	Moment moment = moment(System.currentTimeMillis());
	Moment oldMoment = moment(moment.valueOf() - 1000);
	Date oldDate = oldMoment.toDate();

	assertFalse(moment.isBefore(oldDate));
    }

    @Test
    public void test_isBefore_True_CalendarParam() {
	Moment moment = moment(System.currentTimeMillis());
	Moment futureMoment = moment(System.currentTimeMillis() + 1000);
	Calendar futureCal = futureMoment.toCalendar();

	assertTrue(moment.isBefore(futureCal));
    }

    @Test
    public void test_isBefore_False_CalendarParam_Same() {
	Moment moment = moment(System.currentTimeMillis());
	Moment sameMoment = moment(moment);
	Calendar sameCal = sameMoment.toCalendar();

	assertFalse(moment.isBefore(sameCal));
    }

    @Test
    public void test_isBefore_False_CalendarParam_After() {
	Moment moment = moment(System.currentTimeMillis());
	Moment oldMoment = moment(moment.valueOf() - 1000);
	Calendar oldCal = oldMoment.toCalendar();

	assertFalse(moment.isBefore(oldCal));
    }

    @Test
    public void test_isBefore_True_LongParam() {
	Moment moment = moment(System.currentTimeMillis());
	Moment futureMoment = moment(System.currentTimeMillis() + 1000);
	long future = futureMoment.valueOf();

	assertTrue(moment.isBefore(future));
    }

    @Test
    public void test_isBefore_False_LongParam_Same() {
	Moment moment = moment(System.currentTimeMillis());
	Moment sameMoment = moment(moment);
	long same = sameMoment.valueOf();

	assertFalse(moment.isBefore(same));
    }

    @Test
    public void test_isBefore_False_LongParam_After() {
	Moment moment = moment(System.currentTimeMillis());
	Moment oldMoment = moment(moment.valueOf() - 1000);
	long old = oldMoment.valueOf();

	assertFalse(moment.isBefore(old));
    }

    @Test
    public void test_isBefore_With_CalendarField_MomentParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	Moment futureMoment = moment(date, dateFormat).add(1, Calendar.MONTH);

	assertTrue(moment.isBefore(futureMoment, Calendar.MONTH));
	assertFalse(moment.isBefore(futureMoment, Calendar.YEAR));
    }

    @Test
    public void test_isBefore_With_CalendarField_DateParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	Moment futureMoment = moment(date, dateFormat).add(1, Calendar.MONTH);
	Date futureDate = futureMoment.toDate();

	assertTrue(moment.isBefore(futureDate, Calendar.MONTH));
	assertFalse(moment.isBefore(futureDate, Calendar.YEAR));
    }

    @Test
    public void test_isBefore_With_CalendarField_CalendarParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	Moment futureMoment = moment(date, dateFormat).add(1, Calendar.MONTH);
	Calendar futureCalendar = futureMoment.toCalendar();

	assertTrue(moment.isBefore(futureCalendar, Calendar.MONTH));
	assertFalse(moment.isBefore(futureCalendar, Calendar.YEAR));
    }

    @Test
    public void test_isBefore_With_CalendarField_LongParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	Moment futureMoment = moment(date, dateFormat).add(1, Calendar.MONTH);
	long futureLong = futureMoment.valueOf();

	assertTrue(moment.isBefore(futureLong, Calendar.MONTH));
	assertFalse(moment.isBefore(futureLong, Calendar.YEAR));
    }

    /***
     * isAfter Tests
     * 
     * 
     * 
     * 
     * 
     */

    @Test
    public void test_isAfter_True_MomentParam() {
	Moment moment = moment(System.currentTimeMillis());
	Moment futureMoment = moment(System.currentTimeMillis() - 1000);

	assertTrue(moment.isAfter(futureMoment));
    }

    @Test
    public void test_isAfter_False_MomentParam_Same() {
	Moment moment = moment(System.currentTimeMillis());
	Moment sameMoment = moment(moment);

	assertFalse(moment.isAfter(sameMoment));
    }

    @Test
    public void test_isAfter_False_MomentParam_After() {
	Moment moment = moment(System.currentTimeMillis());
	Moment oldMoment = moment(moment.valueOf() + 1000);

	assertFalse(moment.isAfter(oldMoment));
    }

    @Test
    public void test_isAfter_True_DateParam() {
	Moment moment = moment(System.currentTimeMillis());
	Moment futureMoment = moment(System.currentTimeMillis() - 1000);
	Date futureDate = futureMoment.toDate();

	assertTrue(moment.isAfter(futureDate));
    }

    @Test
    public void test_isAfter_False_DateParam_Same() {
	Moment moment = moment(System.currentTimeMillis());
	Moment sameMoment = moment(moment);
	Date sameDate = sameMoment.toDate();

	assertFalse(moment.isAfter(sameDate));
    }

    @Test
    public void test_isAfter_False_DateParam_After() {
	Moment moment = moment(System.currentTimeMillis());
	Moment oldMoment = moment(moment.valueOf() + 1000);
	Date oldDate = oldMoment.toDate();

	assertFalse(moment.isAfter(oldDate));
    }

    @Test
    public void test_isAfter_True_CalendarParam() {
	Moment moment = moment(System.currentTimeMillis());
	Moment futureMoment = moment(System.currentTimeMillis() - 1000);
	Calendar futureCal = futureMoment.toCalendar();

	assertTrue(moment.isAfter(futureCal));
    }

    @Test
    public void test_isAfter_False_CalendarParam_Same() {
	Moment moment = moment(System.currentTimeMillis());
	Moment sameMoment = moment(moment);
	Calendar sameCal = sameMoment.toCalendar();

	assertFalse(moment.isAfter(sameCal));
    }

    @Test
    public void test_isAfter_False_CalendarParam_After() {
	Moment moment = moment(System.currentTimeMillis());
	Moment oldMoment = moment(moment.valueOf() + 1000);
	Calendar oldCal = oldMoment.toCalendar();

	assertFalse(moment.isAfter(oldCal));
    }

    @Test
    public void test_isAfter_True_LongParam() {
	Moment moment = moment(System.currentTimeMillis());
	Moment futureMoment = moment(System.currentTimeMillis() - 1000);
	long future = futureMoment.valueOf();

	assertTrue(moment.isAfter(future));
    }

    @Test
    public void test_isAfter_False_LongParam_Same() {
	Moment moment = moment(System.currentTimeMillis());
	Moment sameMoment = moment(moment);
	long same = sameMoment.valueOf();

	assertFalse(moment.isAfter(same));
    }

    @Test
    public void test_isAfter_False_LongParam_After() {
	Moment moment = moment(System.currentTimeMillis());
	Moment oldMoment = moment(moment.valueOf() + 1000);
	long old = oldMoment.valueOf();

	assertFalse(moment.isAfter(old));
    }

    @Test
    public void test_isAfter_With_CalendarField_MomentParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	Moment futureMoment = moment(date, dateFormat).subtract(1, Calendar.MONTH);

	assertTrue(moment.isAfter(futureMoment, Calendar.MONTH));
	assertFalse(moment.isAfter(futureMoment, Calendar.YEAR));
    }

    @Test
    public void test_isAfter_With_CalendarField_DateParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	Moment futureMoment = moment(date, dateFormat).subtract(1, Calendar.MONTH);
	Date futureDate = futureMoment.toDate();

	assertTrue(moment.isAfter(futureDate, Calendar.MONTH));
	assertFalse(moment.isAfter(futureDate, Calendar.YEAR));
    }

    @Test
    public void test_isAfter_With_CalendarField_CalendarParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	Moment futureMoment = moment(date, dateFormat).subtract(1, Calendar.MONTH);
	Calendar futureCalendar = futureMoment.toCalendar();

	assertTrue(moment.isAfter(futureCalendar, Calendar.MONTH));
	assertFalse(moment.isAfter(futureCalendar, Calendar.YEAR));
    }

    @Test
    public void test_isAfter_With_CalendarField_LongParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment moment = moment(date, dateFormat);
	Moment futureMoment = moment(date, dateFormat).subtract(1, Calendar.MONTH);
	long futureLong = futureMoment.valueOf();

	assertTrue(moment.isAfter(futureLong, Calendar.MONTH));
	assertFalse(moment.isAfter(futureLong, Calendar.YEAR));
    }

    @Test
    public void test_compareTo_Negative() {
	Moment lhs = moment(System.currentTimeMillis() - 1000);
	Moment rhs = moment(System.currentTimeMillis() + 1000);

	int result = lhs.compareTo(rhs);

	assertThat(result, lessThan(0));
    }

    @Test
    public void test_compareTo_Positive() {
	Moment lhs = moment(System.currentTimeMillis() + 1000);
	Moment rhs = moment(System.currentTimeMillis() - 1000);

	int result = lhs.compareTo(rhs);

	assertThat(result, greaterThan(0));
    }

    @Test
    public void test_compareTo_Zero() {
	Moment lhs = moment(System.currentTimeMillis());
	Moment rhs = moment(lhs.valueOf());

	int result = lhs.compareTo(rhs);

	assertThat(result, equalTo(0));
    }

    @Test
    public void test_isSame_MomentParam() {
	Moment m1 = moment();
	Moment m2 = moment(m1);
	assertTrue(m1.isSame(m2));

	m2.add(1, Calendar.MILLISECOND);
	assertFalse(m1.isSame(m2));
    }

    @Test
    public void test_isSame_DateParam() {
	Moment m1 = moment();
	Moment m2 = moment(m1);
	assertTrue(m1.isSame(m2.toDate()));

	m2.add(1, Calendar.MILLISECOND);
	assertFalse(m1.isSame(m2.toDate()));
    }

    @Test
    public void test_isSame_CalendarParam() {
	Moment m1 = moment();
	Moment m2 = moment(m1);
	assertTrue(m1.isSame(m2.toCalendar()));

	m2.add(1, Calendar.MILLISECOND);
	assertFalse(m1.isSame(m2.toCalendar()));
    }

    @Test
    public void test_isSame_LongParam() {
	Moment m1 = moment();
	Moment m2 = moment(m1);
	assertTrue(m1.isSame(m2.valueOf()));

	m2.add(1, Calendar.MILLISECOND);
	assertFalse(m1.isSame(m2.valueOf()));
    }

    @Test
    public void test_isSame_MomentParam_WithCalendarField() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1).add(1, Calendar.DATE);

	assertTrue(m1.isSame(m2, Calendar.YEAR));
	assertTrue(m1.isSame(m2, Calendar.MONTH));
	assertFalse(m1.isSame(m2, Calendar.DATE));
	assertFalse(m1.isSame(m2, Calendar.HOUR));
	assertFalse(m1.isSame(m2, Calendar.MINUTE));
    }

    @Test
    public void test_isSame_DateParam_WithCalendarField() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1).add(1, Calendar.DATE);

	assertTrue(m1.isSame(m2.toDate(), Calendar.YEAR));
	assertTrue(m1.isSame(m2.toDate(), Calendar.MONTH));
	assertFalse(m1.isSame(m2.toDate(), Calendar.DATE));
	assertFalse(m1.isSame(m2.toDate(), Calendar.HOUR));
	assertFalse(m1.isSame(m2.toDate(), Calendar.MINUTE));
    }

    @Test
    public void test_isSame_CalendarParam_WithCalendarField() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1).add(1, Calendar.DATE);

	assertTrue(m1.isSame(m2.toCalendar(), Calendar.YEAR));
	assertTrue(m1.isSame(m2.toCalendar(), Calendar.MONTH));
	assertFalse(m1.isSame(m2.toCalendar(), Calendar.DATE));
	assertFalse(m1.isSame(m2.toCalendar(), Calendar.HOUR));
	assertFalse(m1.isSame(m2.toCalendar(), Calendar.MINUTE));
    }

    @Test
    public void test_isSame_LongParam_WithCalendarField() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1).add(1, Calendar.DATE);

	assertTrue(m1.isSame(m2.valueOf(), Calendar.YEAR));
	assertTrue(m1.isSame(m2.valueOf(), Calendar.MONTH));
	assertFalse(m1.isSame(m2.valueOf(), Calendar.DATE));
	assertFalse(m1.isSame(m2.valueOf(), Calendar.HOUR));
	assertFalse(m1.isSame(m2.valueOf(), Calendar.MINUTE));
    }

    @Test
    public void test_isSameOrBefore_MomentParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1);
	Moment m3 = moment(m1).add(1, Calendar.HOUR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrBefore(m2));
	assertTrue(m1.isSameOrBefore(m3));
	assertFalse(m1.isSameOrBefore(m4));
    }

    @Test
    public void test_isSameOrBefore_DateParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1);
	Moment m3 = moment(m1).add(1, Calendar.HOUR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrBefore(m2.toDate()));
	assertTrue(m1.isSameOrBefore(m3.toDate()));
	assertFalse(m1.isSameOrBefore(m4.toDate()));
    }

    @Test
    public void test_isSameOrBefore_CalendarParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1);
	Moment m3 = moment(m1).add(1, Calendar.HOUR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrBefore(m2.toCalendar()));
	assertTrue(m1.isSameOrBefore(m3.toCalendar()));
	assertFalse(m1.isSameOrBefore(m4.toCalendar()));
    }

    @Test
    public void test_isSameOrBefore_LongParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1);
	Moment m3 = moment(m1).add(1, Calendar.HOUR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrBefore(m2.valueOf()));
	assertTrue(m1.isSameOrBefore(m3.valueOf()));
	assertFalse(m1.isSameOrBefore(m4.valueOf()));
    }

    @Test
    public void test_isSameOrBefore_MomentParam_WithCalendarField() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1).add(1, Calendar.MONTH);
	Moment m3 = moment(m1).add(1, Calendar.YEAR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrBefore(m2, Calendar.YEAR));
	assertTrue(m1.isSameOrBefore(m3, Calendar.YEAR));
	assertFalse(m1.isSameOrBefore(m4, Calendar.DATE));
	assertTrue(m1.isSameOrBefore(m4, Calendar.MONTH));
    }

    @Test
    public void test_isSameOrBefore_DateParam_WithCalendarField() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1).add(1, Calendar.MONTH);
	Moment m3 = moment(m1).add(1, Calendar.YEAR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrBefore(m2.toDate(), Calendar.YEAR));
	assertTrue(m1.isSameOrBefore(m3.toDate(), Calendar.YEAR));
	assertFalse(m1.isSameOrBefore(m4.toDate(), Calendar.DATE));
	assertTrue(m1.isSameOrBefore(m4.toDate(), Calendar.MONTH));
    }

    @Test
    public void test_isSameOrBefore_CalendarParam_WithCalendarField() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1).add(1, Calendar.MONTH);
	Moment m3 = moment(m1).add(1, Calendar.YEAR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrBefore(m2.toCalendar(), Calendar.YEAR));
	assertTrue(m1.isSameOrBefore(m3.toCalendar(), Calendar.YEAR));
	assertFalse(m1.isSameOrBefore(m4.toCalendar(), Calendar.DATE));
	assertTrue(m1.isSameOrBefore(m4.toCalendar(), Calendar.MONTH));
    }

    @Test
    public void test_isSameOrBefore_LongParam_WithCalendarField() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1).add(1, Calendar.MONTH);
	Moment m3 = moment(m1).add(1, Calendar.YEAR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrBefore(m2.valueOf(), Calendar.YEAR));
	assertTrue(m1.isSameOrBefore(m3.valueOf(), Calendar.YEAR));
	assertFalse(m1.isSameOrBefore(m4.valueOf(), Calendar.DATE));
	assertTrue(m1.isSameOrBefore(m4.valueOf(), Calendar.MONTH));
    }

    // After tests
    // After tests
    // After tests
    // After tests
    // After tests
    // After tests

    @Test
    public void test_isSameOrAfter_MomentParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1);
	Moment m3 = moment(m1).add(1, Calendar.HOUR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrAfter(m2));
	assertFalse(m1.isSameOrAfter(m3));
	assertTrue(m1.isSameOrAfter(m4));
    }

    @Test
    public void test_isSameOrAfter_DateParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1);
	Moment m3 = moment(m1).add(1, Calendar.HOUR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrAfter(m2.toDate()));
	assertFalse(m1.isSameOrAfter(m3.toDate()));
	assertTrue(m1.isSameOrAfter(m4.toDate()));
    }

    @Test
    public void test_isSameOrAfter_CalendarParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1);
	Moment m3 = moment(m1).add(1, Calendar.HOUR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrAfter(m2.toCalendar()));
	assertFalse(m1.isSameOrAfter(m3.toCalendar()));
	assertTrue(m1.isSameOrAfter(m4.toCalendar()));
    }

    @Test
    public void test_isSameOrAfter_LongParam() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1);
	Moment m3 = moment(m1).add(1, Calendar.HOUR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrAfter(m2.valueOf()));
	assertFalse(m1.isSameOrAfter(m3.valueOf()));
	assertTrue(m1.isSameOrAfter(m4.valueOf()));
    }

    @Test
    public void test_isSameOrAfter_MomentParam_WithCalendarField() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1).add(1, Calendar.MONTH);
	Moment m3 = moment(m1).add(1, Calendar.YEAR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrAfter(m2, Calendar.YEAR));
	assertFalse(m1.isSameOrAfter(m3, Calendar.YEAR));
	assertTrue(m1.isSameOrAfter(m4, Calendar.DATE));
	assertTrue(m1.isSameOrAfter(m4, Calendar.MONTH));
    }

    @Test
    public void test_isSameOrAfter_DateParam_WithCalendarField() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1).add(1, Calendar.MONTH);
	Moment m3 = moment(m1).add(1, Calendar.YEAR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrAfter(m2.toDate(), Calendar.YEAR));
	assertFalse(m1.isSameOrAfter(m3.toDate(), Calendar.YEAR));
	assertTrue(m1.isSameOrAfter(m4.toDate(), Calendar.DATE));
	assertTrue(m1.isSameOrAfter(m4.toDate(), Calendar.MONTH));
    }

    @Test
    public void test_isSameOrAfter_CalendarParam_WithCalendarField() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1).add(1, Calendar.MONTH);
	Moment m3 = moment(m1).add(1, Calendar.YEAR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrAfter(m2.toCalendar(), Calendar.YEAR));
	assertFalse(m1.isSameOrAfter(m3.toCalendar(), Calendar.YEAR));
	assertTrue(m1.isSameOrAfter(m4.toCalendar(), Calendar.DATE));
	assertTrue(m1.isSameOrAfter(m4.toCalendar(), Calendar.MONTH));
    }

    @Test
    public void test_isSameOrAfter_LongParam_WithCalendarField() {
	String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	String date = "2016-03-15 23:36:12.532";

	Moment m1 = moment(date, dateFormat);
	Moment m2 = moment(m1).add(1, Calendar.MONTH);
	Moment m3 = moment(m1).add(1, Calendar.YEAR);
	Moment m4 = moment(m1).subtract(1, Calendar.DATE);

	assertTrue(m1.isSameOrAfter(m2.valueOf(), Calendar.YEAR));
	assertFalse(m1.isSameOrAfter(m3.valueOf(), Calendar.YEAR));
	assertTrue(m1.isSameOrAfter(m4.valueOf(), Calendar.DATE));
	assertTrue(m1.isSameOrAfter(m4.valueOf(), Calendar.MONTH));
    }
}
