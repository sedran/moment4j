package com.asosyalbebe.moment4j;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Serdar Kuzucu
 *
 */
public class Moment implements Cloneable, Serializable, Comparable<Moment> {
    private static final long serialVersionUID = 1L;

    /**
     * Internal state of the moment instance is hold on this calendar instance. 
     * Moment does not expose this calendar instance outside.
     * Only a copy of this calendar can be retrieved from the outside of the Moment instance.
     */
    private final Calendar calendar;

    /**
     * Creates a moment instance for current time
     */
    private Moment() {
	this.calendar = Calendar.getInstance();
    }

    /**
     * Creates a moment instance for given date as string which will be parsed with given pattern
     * 
     * @param dateString date as string to be parsed and encapsulated
     * @param pattern pattern to be used to parse the dateString
     */
    private Moment(String dateString, String pattern) {
	Date date;
	try {
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    date = format.parse(dateString);
	} catch (Exception e) {
	    throw new MomentException("Parse error occurred while parsing [" + dateString + "] with SimpleDateFormat [" + pattern + "]", e);
	}
	this.calendar = Calendar.getInstance();
	this.calendar.setTime(date);
    }

    /**
     * Creates a moment instance by encapsulating the given calendar instance. 
     * The given calendar is cloned. The operations on this moment have no effect on the given calendar instance.
     * 
     * @param calendar calendar to be cloned and encapsulated
     */
    private Moment(Calendar calendar) {
	this.calendar = (Calendar) calendar.clone();
    }

    /**
     * Creates a moment instance by using the given date. The date parameter is copied. 
     * The operations on this moment have no effect on the given date instance.
     * 
     * @param date date to be used by this Moment instance.
     */
    private Moment(Date date) {
	this();
	this.calendar.setTimeInMillis(date.getTime());
    }

    /**
     * Creates a moment instance by using the given unix timestamp
     * 
     * @param timeInMillis
     */
    private Moment(long timeInMillis) {
	this();
	this.calendar.setTimeInMillis(timeInMillis);
    }

    /**
     * Creates a moment instance by using the given array of values. 
     * The array must contain exactly 7 integers, which represents years, months, days, hours, minutes, seconds, and milliseconds.
     * 
     * For example, the following code can be used to create 31/01/2015 13:32:25.125
     * 
     * <pre>
     * {@code
     * int[] arrayOfIntegers = new int[] {2015, 0, 31, 13, 32, 25, 125};
     * Moment moment = Moment.moment(arrayOfIntegers);
     * }
     * </pre>
     * 
     * Note that the first month in Java(January) is represented by 0.
     * 
     * @param array
     */
    private Moment(int[] array) {
	this();

	if (array == null) {
	    throw new MomentException("int[] array parameter cannot be null!");
	}

	if (array.length != 7) {
	    throw new MomentException("int[] array must have exactly 7 elements! You provided " + array.length);
	}

	this.calendar.set(Calendar.YEAR, array[0]);
	this.calendar.set(Calendar.MONTH, array[1]);
	this.calendar.set(Calendar.DAY_OF_MONTH, array[2]);
	this.calendar.set(Calendar.HOUR_OF_DAY, array[3]);
	this.calendar.set(Calendar.MINUTE, array[4]);
	this.calendar.set(Calendar.SECOND, array[5]);
	this.calendar.set(Calendar.MILLISECOND, array[6]);
    }

    /**
     * Sets the milliseconds.
     * Accepts numbers from 0 to 999. If the range is exceeded, it will bubble up to the seconds.
     * 
     * @param milliseconds milliseconds to set.
     * @return this Moment instance for chainability
     */
    public Moment milliseconds(int milliseconds) {
	return updateCalendarField(Calendar.MILLISECOND, milliseconds);
    }

    /**
     * Returns the milliseconds. The returned value is always in range from 0 to 999.
     * 
     * @return The milliseconds. The returned value is always in range from 0 to 999.
     */
    public int milliseconds() {
	return this.calendar.get(Calendar.MILLISECOND);
    }

    public Moment seconds(int seconds) {
	return updateCalendarField(Calendar.SECOND, seconds);
    }

    public int seconds() {
	return this.calendar.get(Calendar.SECOND);
    }

    public Moment minutes(int minutes) {
	return updateCalendarField(Calendar.MINUTE, minutes);
    }

    public int minutes() {
	return this.calendar.get(Calendar.MINUTE);
    }

    public Moment hours(int hours) {
	return updateCalendarField(Calendar.HOUR_OF_DAY, hours);
    }

    public int hours() {
	return this.calendar.get(Calendar.HOUR_OF_DAY);
    }

    public Moment dates(int dayOfMonth) {
	return updateCalendarField(Calendar.DAY_OF_MONTH, dayOfMonth);
    }

    public int dates() {
	return this.calendar.get(Calendar.DAY_OF_MONTH);
    }

    public Moment days(int dayOfWeek) {
	return updateCalendarField(Calendar.DAY_OF_WEEK, dayOfWeek);
    }

    public int days() {
	return this.calendar.get(Calendar.DAY_OF_WEEK);
    }

    public Moment months(int months) {
	return updateCalendarField(Calendar.MONTH, months);
    }

    public int months() {
	return this.calendar.get(Calendar.MONTH);
    }

    public Moment years(int years) {
	return updateCalendarField(Calendar.YEAR, years);
    }

    public int years() {
	return this.calendar.get(Calendar.YEAR);
    }

    public Moment dayOfYear(int dayOfYear) {
	return updateCalendarField(Calendar.DAY_OF_YEAR, dayOfYear);
    }

    public int dayOfYear() {
	return this.calendar.get(Calendar.DAY_OF_YEAR);
    }

    public Moment set(int calendarField, int value) {
	return updateCalendarField(calendarField, value);
    }

    public int get(int calendarField) {
	return this.calendar.get(calendarField);
    }

    public Moment add(int value, int calendarField) {
	return addCalendarField(calendarField, value);
    }

    public Moment subtract(int value, int calendarField) {
	return addCalendarField(calendarField, -value);
    }

    public Moment startOf(int calendarField) {
	switch (calendarField) {
	case Calendar.YEAR:
	    this.months(0);
	case Calendar.MONTH:
	    this.dates(1);
	case Calendar.WEEK_OF_YEAR:
	case Calendar.WEEK_OF_MONTH:
	case Calendar.DAY_OF_MONTH:
	case Calendar.DAY_OF_WEEK:
	case Calendar.DAY_OF_WEEK_IN_MONTH:
	case Calendar.DAY_OF_YEAR:
	    this.hours(0);
	case Calendar.HOUR:
	case Calendar.HOUR_OF_DAY:
	    this.minutes(0);
	case Calendar.MINUTE:
	    this.seconds(0);
	case Calendar.SECOND:
	    this.milliseconds(0);
	}

	if (calendarField == Calendar.WEEK_OF_MONTH || calendarField == Calendar.WEEK_OF_YEAR) {
	    this.days(1);
	}

	if (calendarField == Calendar.DAY_OF_WEEK_IN_MONTH) {
	    int dayOfWeekInMonth = this.calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	    this.dates(0);
	    this.calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, dayOfWeekInMonth);
	}

	return this;
    }

    public Moment endOf(int calendarField) {
	if (calendarField == Calendar.MILLISECOND) {
	    return this;
	}

	return this.startOf(calendarField).add(1, calendarField).subtract(1, Calendar.MILLISECOND);
    }

    public Date toDate() {
	return this.calendar.getTime();
    }

    public Calendar toCalendar() {
	return (Calendar) this.calendar.clone();
    }

    public int[] toArray() {
	int[] array = new int[7];
	array[0] = this.calendar.get(Calendar.YEAR);
	array[1] = this.calendar.get(Calendar.MONTH);
	array[2] = this.calendar.get(Calendar.DAY_OF_MONTH);
	array[3] = this.calendar.get(Calendar.HOUR_OF_DAY);
	array[4] = this.calendar.get(Calendar.MINUTE);
	array[5] = this.calendar.get(Calendar.SECOND);
	array[6] = this.calendar.get(Calendar.MILLISECOND);
	return array;
    }

    public long valueOf() {
	return this.calendar.getTimeInMillis();
    }

    public long unix() {
	return valueOf() / 1000;
    }

    @Override
    public Moment clone() {
	return new Moment((Calendar) this.calendar.clone());
    }

    public boolean isBefore(Moment moment) {
	return isBefore(moment.valueOf());
    }

    public boolean isBefore(Date date) {
	return isBefore(date.getTime());
    }

    public boolean isBefore(Calendar calendar) {
	return isBefore(calendar.getTimeInMillis());
    }

    public boolean isBefore(long milliseconds) {
	return this.valueOf() < milliseconds;
    }

    public boolean isBefore(Moment moment, int calendarField) {
	return isBefore(moment.valueOf(), calendarField);
    }

    public boolean isBefore(Date date, int calendarField) {
	return isBefore(date.getTime(), calendarField);
    }

    public boolean isBefore(Calendar calendar, int calendarField) {
	return isBefore(calendar.getTime(), calendarField);
    }

    public boolean isBefore(long milliseconds, int calendarField) {
	return this.clone().endOf(calendarField).isBefore(milliseconds);
    }

    public boolean isAfter(Moment moment) {
	return isAfter(moment.valueOf());
    }

    public boolean isAfter(Date date) {
	return isAfter(date.getTime());
    }

    public boolean isAfter(Calendar calendar) {
	return isAfter(calendar.getTimeInMillis());
    }

    public boolean isAfter(long milliseconds) {
	return this.valueOf() > milliseconds;
    }

    public boolean isAfter(Moment moment, int calendarField) {
	return isAfter(moment.valueOf(), calendarField);
    }

    public boolean isAfter(Date date, int calendarField) {
	return isAfter(date.getTime(), calendarField);
    }

    public boolean isAfter(Calendar calendar, int calendarField) {
	return isAfter(calendar.getTimeInMillis(), calendarField);
    }

    public boolean isAfter(long milliseconds, int calendarField) {
	return this.clone().startOf(calendarField).isAfter(milliseconds);
    }

    public int compareTo(Moment o) {
	return Long.compare(this.valueOf(), o.valueOf());
    }

    public boolean isSame(Moment moment) {
	return isSame(moment.valueOf());
    }

    public boolean isSame(Date date) {
	return isSame(date.getTime());
    }

    public boolean isSame(Calendar calendar) {
	return isSame(calendar.getTimeInMillis());
    }

    public boolean isSame(long milliseconds) {
	return this.valueOf() == milliseconds;
    }

    public boolean isSame(Moment moment, int calendarField) {
	return this.clone().startOf(calendarField).isSame(moment.clone().startOf(calendarField));
    }

    public boolean isSame(Date date, int calendarField) {
	return this.clone().startOf(calendarField).isSame(moment(date).startOf(calendarField));
    }

    public boolean isSame(Calendar calendar, int calendarField) {
	return this.clone().startOf(calendarField).isSame(moment(calendar).startOf(calendarField));
    }

    public boolean isSame(long milliseconds, int calendarField) {
	return this.clone().startOf(calendarField).isSame(moment(milliseconds).startOf(calendarField));
    }

    public boolean isSameOrBefore(Moment moment) {
	return isBefore(moment) || isSame(moment);
    }

    public boolean isSameOrBefore(Date date) {
	return isBefore(date) || isSame(date);
    }

    public boolean isSameOrBefore(Calendar calendar) {
	return isBefore(calendar) || isSame(calendar);
    }

    public boolean isSameOrBefore(long milliseconds) {
	return isBefore(milliseconds) || isSame(milliseconds);
    }

    public boolean isSameOrBefore(Moment moment, int calendarField) {
	return isBefore(moment, calendarField) || isSame(moment, calendarField);
    }

    public boolean isSameOrBefore(Date date, int calendarField) {
	return isBefore(date, calendarField) || isSame(date, calendarField);
    }

    public boolean isSameOrBefore(Calendar calendar, int calendarField) {
	return isBefore(calendar, calendarField) || isSame(calendar, calendarField);
    }

    public boolean isSameOrBefore(long milliseconds, int calendarField) {
	return isBefore(milliseconds, calendarField) || isSame(milliseconds, calendarField);
    }

    public boolean isSameOrAfter(Moment moment) {
	return isAfter(moment) || isSame(moment);
    }

    public boolean isSameOrAfter(Date date) {
	return isAfter(date) || isSame(date);
    }

    public boolean isSameOrAfter(Calendar calendar) {
	return isAfter(calendar) || isSame(calendar);
    }

    public boolean isSameOrAfter(long milliseconds) {
	return isAfter(milliseconds) || isSame(milliseconds);
    }

    public boolean isSameOrAfter(Moment moment, int calendarField) {
	return isAfter(moment, calendarField) || isSame(moment, calendarField);
    }

    public boolean isSameOrAfter(Date date, int calendarField) {
	return isAfter(date, calendarField) || isSame(date, calendarField);
    }

    public boolean isSameOrAfter(Calendar calendar, int calendarField) {
	return isAfter(calendar, calendarField) || isSame(calendar, calendarField);
    }

    public boolean isSameOrAfter(long milliseconds, int calendarField) {
	return isAfter(milliseconds, calendarField) || isSame(milliseconds, calendarField);
    }

    public boolean isBetween(Moment from, Moment to) {
	return isAfter(from) && isBefore(to);
    }

    public boolean isBetween(Date from, Date to) {
	return isAfter(from) && isBefore(to);
    }

    public boolean isBetween(Calendar from, Calendar to) {
	return isAfter(from) && isBefore(to);
    }

    public boolean isBetween(long fromMillis, long toMillis) {
	return isAfter(fromMillis) && isBefore(toMillis);
    }

    public boolean isBetween(Moment from, Moment to, int calendarField) {
	return isAfter(from, calendarField) && isBefore(to, calendarField);
    }

    public boolean isBetween(Date from, Date to, int calendarField) {
	return isAfter(from, calendarField) && isBefore(to, calendarField);
    }

    public boolean isBetween(Calendar from, Calendar to, int calendarField) {
	return isAfter(from, calendarField) && isBefore(to, calendarField);
    }

    public boolean isBetween(long fromMillis, long toMillis, int calendarField) {
	return isAfter(fromMillis, calendarField) && isBefore(toMillis, calendarField);
    }

    /**
     * Returns true if that year is a leap year, and false if it is not.
     * @return true if that year is a leap year, and false if it is not.
     */
    public boolean isLeapYear() {
	return isLeapYear(years());
    }

    @Override
    public int hashCode() {
	Long value = valueOf();
	return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Moment other = (Moment) obj;

	return isSame(other);
    }

    private Moment updateCalendarField(int field, int value) {
	this.calendar.set(field, value);
	return this;
    }

    private Moment addCalendarField(int field, int value) {
	this.calendar.add(field, value);
	return this;
    }

    public static Moment max(Moment... moments) {
	if (moments == null || moments.length == 0) {
	    return moment();
	}

	Moment max = moments[0];
	for (int i = 1; i < moments.length; i++) {
	    if (moments[i].valueOf() > max.valueOf()) {
		max = moments[i];
	    }
	}

	return max;
    }

    public static Moment min(Moment... moments) {
	if (moments == null || moments.length == 0) {
	    return moment();
	}

	Moment min = moments[0];
	for (int i = 1; i < moments.length; i++) {
	    if (moments[i].valueOf() < min.valueOf()) {
		min = moments[i];
	    }
	}

	return min;
    }

    /**
     * Returns true if the given year is a leap year, and false if it is not.
     * @param year year to control if it is leap or not
     * @return true if the given year is a leap year, and false if it is not.
     */
    public static boolean isLeapYear(int year) {
	return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public static Moment moment() {
	return new Moment();
    }

    public static Moment moment(String dateString, String pattern) {
	return new Moment(dateString, pattern);
    }

    public static Moment moment(Calendar calendar) {
	return new Moment(calendar);
    }

    public static Moment moment(Date date) {
	return new Moment(date);
    }

    public static Moment moment(long timeInMillis) {
	return new Moment(timeInMillis);
    }

    public static Moment moment(int[] array) {
	return new Moment(array);
    }

    public static Moment moment(Moment source) {
	return new Moment(source.valueOf());
    }
}
