package com.asosyalbebe.moment4j;

import com.asosyalbebe.moment4j.fault.MomentException;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static com.asosyalbebe.moment4j.Moment.moment;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;

public class MomentConstructionTest {

    @Test
    public void test_ConstructionWithNoParam() {
        long testStartTime = System.currentTimeMillis();
        long testStartTimeUnix = testStartTime / 1000;

        Moment moment = moment();
        assertNotNull(moment.toDate());
        assertNotNull(moment.toCalendar());
        assertNotNull(moment.toArray());
        assertEquals(7, moment.toArray().length);

        assertThat(moment.valueOf(), greaterThanOrEqualTo(testStartTime));
        assertThat(moment.unix(), greaterThanOrEqualTo(testStartTimeUnix));
    }

    @Test
    public void test_FormatConstructor() {
        String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
        String date = "2016-03-15 23:36:12.532";

        Moment moment = moment(date, dateFormat);
        Calendar calendar = moment.toCalendar();

        assertEquals(2016, calendar.get(Calendar.YEAR));
        assertEquals(2, calendar.get(Calendar.MONTH));
        assertEquals(15, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(23, calendar.get(Calendar.HOUR_OF_DAY));
        assertEquals(36, calendar.get(Calendar.MINUTE));
        assertEquals(12, calendar.get(Calendar.SECOND));
        assertEquals(532, calendar.get(Calendar.MILLISECOND));
    }

    @Test(expected = MomentException.class)
    public void test_FormatConstructor_IllegalPattern_MomentException() {
        String dateFormat = "yyyy-MM-dd ASDFG HH:mm:ss.SSS";
        String date = "2016-03-15 23:36:12.532";

        moment(date, dateFormat);
    }

    @Test(expected = MomentException.class)
    public void test_FormatConstructor_IllegalDate_MomentException() {
        String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
        String date = "2016-03-15 -?! 23:36:12.532";
        moment(date, dateFormat);
    }

    @Test
    public void test_CalendarConstructor() {
        long timeInMillis = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);

        Moment moment = moment(calendar);
        assertEquals(timeInMillis, moment.valueOf());
    }

    @Test
    public void test_CalendarConstructor_ClonesCalendar() {
        long timeInMillis = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        int currentYear = calendar.get(Calendar.YEAR);

        Moment moment = moment(calendar);
        calendar.set(Calendar.YEAR, currentYear + 1);

        assertNotSame(calendar, moment.toCalendar());
        assertEquals(currentYear + 1, calendar.get(Calendar.YEAR));
        assertEquals(currentYear, moment.toCalendar().get(Calendar.YEAR));
    }

    @Test
    public void test_DateConstructor() {
        Date date = new Date();

        Moment moment = moment(date);

        assertNotSame(date, moment.toDate());
        assertEquals(date.getTime(), moment.valueOf());
    }

    @Test
    public void test_MillisecondConstructor() {
        long timeInMillis = System.currentTimeMillis();

        Moment moment = moment(timeInMillis);
        assertEquals(timeInMillis, moment.valueOf());
    }

    @Test
    public void test_ArrayConstructor() {
        int[] array = new int[]{2016, 2, 15, 23, 31, 15, 251};

        Moment moment = moment(array);
        assertArrayEquals(array, moment.toArray());
    }

    @Test(expected = MomentException.class)
    public void test_NullArrayConstructor() {
        int[] array = null;
        moment(array);
    }

    @Test(expected = MomentException.class)
    public void test_InvalidLengthArrayConstructor_LessArguments() {
        int[] array = new int[]{2016, 2, 15, 23, 31, 15};
        moment(array);
    }

    @Test(expected = MomentException.class)
    public void test_InvalidLengthArrayConstructor_MoreArguments() {
        int[] array = new int[]{2016, 2, 15, 23, 31, 15, 231, 21};
        moment(array);
    }

    @Test
    public void test_MomentConstructor() {
        Moment moment1 = moment();
        Moment moment2 = moment(moment1);

        assertNotSame(moment1, moment2);
        assertEquals(moment1.valueOf(), moment2.valueOf());
    }

    @Test
    public void test_clone_moment() {
        Moment moment = moment();
        Moment clone = moment.clone();
        assertFalse(clone.isBefore(moment));
        assertFalse(clone.isAfter(moment));
    }

}
