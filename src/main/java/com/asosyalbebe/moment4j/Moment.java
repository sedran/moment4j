package com.asosyalbebe.moment4j;

import com.asosyalbebe.moment4j.fault.MomentException;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.asosyalbebe.moment4j.util.FormatUtils.toThreeDigitsString;
import static com.asosyalbebe.moment4j.util.FormatUtils.toTwoDigitsString;

/**
 * The class <code>Moment</code> represents a specific instant
 * in time, with millisecond precision.
 * <p>
 * Moment also provides a very useful set of APIs to make it easy to manipulate dates in Java.
 *
 * @author Serdar Kuzucu
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
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
     * @param pattern    pattern to be used to parse the dateString
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
     * @param timeInMillis time in milliseconds
     */
    private Moment(long timeInMillis) {
        this();
        this.calendar.setTimeInMillis(timeInMillis);
    }

    /**
     * Creates a moment instance by using the given array of values.
     * <p>
     * The array must contain exactly 7 integers, which represents years, months, days, hours, minutes, seconds, and milliseconds.
     * <p>
     * For example, the following code can be used to create 31/01/2015 13:32:25.125
     *
     * <pre>
     * {@code
     * int[] arrayOfIntegers = new int[] {2015, 0, 31, 13, 32, 25, 125};
     * Moment moment = Moment.moment(arrayOfIntegers);
     * }
     * </pre>
     * <p>
     * Note that the first month in Java(January) is represented by 0.
     *
     * @param array array of 7 integers containing years, months, days, hours, minutes, seconds, and milliseconds
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
     * <p>
     * Accepts numbers from 0 to 999. If the range is exceeded, it will bubble up to the seconds.
     *
     * @param milliseconds milliseconds to set.
     * @return this Moment instance for chainability.
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

    /**
     * Sets the seconds.
     * <p>
     * Accepts numbers from 0 to 59. If the range is exceeded, it will bubble up to the minutes.
     *
     * @param seconds seconds to set.
     * @return this Moment instance for chainability.
     */
    public Moment seconds(int seconds) {
        return updateCalendarField(Calendar.SECOND, seconds);
    }

    /**
     * Returns the seconds. The returned value is always in range from 0 to 59.
     *
     * @return The seconds. The returned value is always in range from 0 to 59.
     */
    public int seconds() {
        return this.calendar.get(Calendar.SECOND);
    }

    /**
     * Sets the minutes.
     * <p>
     * Accepts numbers from 0 to 59. If the range is exceeded, it will bubble up to the hours.
     *
     * @param minutes minutes to set.
     * @return this Moment instance for chainability.
     */
    public Moment minutes(int minutes) {
        return updateCalendarField(Calendar.MINUTE, minutes);
    }

    /**
     * Returns the minutes. The returned value is always in range from 0 to 59.
     *
     * @return The minutes. The returned value is always in range from 0 to 59.
     */
    public int minutes() {
        return this.calendar.get(Calendar.MINUTE);
    }

    /**
     * Sets the hour of the day.
     * <p>
     * Accepts numbers from 0 to 23. If the range is exceeded, it will bubble up to the days.
     *
     * @param hours hours to set.
     * @return this Moment instance for chainability.
     */
    public Moment hours(int hours) {
        return updateCalendarField(Calendar.HOUR_OF_DAY, hours);
    }

    /**
     * Returns the hour of day. The returned value is always in range from 0 to 23.
     *
     * @return The hour of day. The returned value is always in range from 0 to 23.
     */
    public int hours() {
        return this.calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Sets the day of month.
     * <p>
     * Accepts numbers from 0 to 31. If the range is exceeded, it will bubble up to the months.
     *
     * @param dayOfMonth day of month to set.
     * @return this Moment instance for chainability.
     */
    public Moment dates(int dayOfMonth) {
        return updateCalendarField(Calendar.DAY_OF_MONTH, dayOfMonth);
    }

    /**
     * Returns the day of month. The returned value is always in range from 0 to 31.
     *
     * @return The day of month. The returned value is always in range from 0 to 31.
     */
    public int dates() {
        return this.calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Sets the day of week.
     * <p>
     * Accepts numbers from 1 to 7. Sunday is 1, Saturday is 7.
     *
     * @param dayOfWeek day of week to set. Calendar.SUNDAY, Calendar.MONDAY, etc. can be used.
     * @return this Moment instance for chainability.
     */
    public Moment days(int dayOfWeek) {
        return updateCalendarField(Calendar.DAY_OF_WEEK, dayOfWeek);
    }

    /**
     * Returns the day of week. The returned value is always in range from 1 to 7.
     *
     * @return The day of week. The returned value is always in range from 1 to 7.
     */
    public int days() {
        return this.calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Sets the months.
     * <p>
     * Accepts numbers from 0 to 11. If the range is exceeded, it will bubble up to the year.
     * <p>
     * Note: Months are zero indexed, so January is month 0.
     * <p>
     * Calendar.JANUARY, Calendar.FEBRUARY, etc. can be used as argument.
     *
     * @param months months to set
     * @return this Moment instance for chainability.
     */
    public Moment months(int months) {
        return updateCalendarField(Calendar.MONTH, months);
    }

    /**
     * Returns the months. The returned value is always in range from 0 to 11.
     *
     * @return The months. The returned value is always in range from 0 to 11.
     */
    public int months() {
        return this.calendar.get(Calendar.MONTH);
    }

    /**
     * Sets the years.
     *
     * @param years years to set.
     * @return this Moment instance for chainability.
     */
    public Moment years(int years) {
        return updateCalendarField(Calendar.YEAR, years);
    }

    /**
     * Returns the years.
     *
     * @return The years.
     */
    public int years() {
        return this.calendar.get(Calendar.YEAR);
    }

    /**
     * Sets the day of the year.
     * <p>
     * Accepts numbers from 1 to 366. If the range is exceeded, it will bubble up to the years.
     *
     * @param dayOfYear day of year to set. Accepts numbers from 1 to 366. If the range is exceeded, it will bubble up to the years.
     * @return this Moment instance for chainability.
     */
    public Moment dayOfYear(int dayOfYear) {
        return updateCalendarField(Calendar.DAY_OF_YEAR, dayOfYear);
    }

    /**
     * Returns the day of year. The returned value is always in range from 1 to 366.
     *
     * @return The returned value is always in range from 1 to 366.
     */
    public int dayOfYear() {
        return this.calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Sets the given Calendar field to the given value.
     *
     * <pre>
     * {@code
     * Moment moment = Moment.moment().set(Calendar.YEAR, 2012);
     * }
     * </pre>
     *
     * @param calendarField field to set. For example, to update month field, use Calendar.MONTH
     * @param value         value to set.
     * @return this Moment instance for chainability.
     */
    public Moment set(int calendarField, int value) {
        return updateCalendarField(calendarField, value);
    }

    /**
     * Returns the value of the given Calendar field. For example, to get day of month, use Calendar.DAY_OF_MONTH or Calendar.DATE
     *
     * @param calendarField the calendar field whose value will be returned.
     * @return the value of the given Calendar field.
     */
    public int get(int calendarField) {
        return this.calendar.get(calendarField);
    }

    /**
     * Adds the specified amount of time to the given calendar field, based on the wrapped calendar's rules.
     * For example, to add 5 days to the current time of the moment, you can achieve it by calling:
     * <p><code>add(5, Calendar.DAY_OF_MONTH)</code>.
     *
     * @param value         the amount of date or time to be added to the field.
     * @param calendarField the calendar field.
     * @return this Moment instance for chainability.
     */
    public Moment add(int value, int calendarField) {
        return addCalendarField(calendarField, value);
    }

    /**
     * Subtracts the specified amount of time to the given calendar field, based on the wrapped calendar's rules.
     * For example, to subtract 5 days from the current time of the moment, you can achieve it by calling:
     * <p><code>subtract(5, Calendar.DAY_OF_MONTH)</code>.
     *
     * @param value         the amount of date or time to be subtracted from the field.
     * @param calendarField the calendar field.
     * @return this Moment instance for chainability.
     */
    public Moment subtract(int value, int calendarField) {
        return addCalendarField(calendarField, -value);
    }

    /**
     * <p>Mutates the original moment by setting it to the start of a unit of time.</p>
     *
     * <pre>
     * moment().startOf(Calendar.YEAR);
     * moment().startOf(Calendar.MONTH);
     * moment().startOf(Calendar.HOUR);
     * </pre>
     *
     * <p>The above methods are essentially the same as the following.</p>
     *
     * <pre>
     * moment().months(0).days(1).hours(0).minutes(0).seconds(0).milliseconds(0);
     * moment().days(1).hours(0).minutes(0).seconds(0).milliseconds(0);
     * moment().minutes(0).seconds(0).milliseconds(0);
     * </pre>
     *
     * @param calendarField unit of time whose start time will be set to this moment instance.
     * @return this Moment instance for chainability.
     */
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
            case Calendar.MILLISECOND:
                break;
            default:
                throw new MomentException("Unknown calendarField: " + calendarField);
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

    /**
     * <p>Mutates the original moment by setting it to the end of a unit of time.</p>
     *
     * <pre>
     * moment().endOf(Calendar.YEAR);
     * moment().endOf(Calendar.MONTH);
     * moment().endOf(Calendar.HOUR);
     * </pre>
     *
     * <p>The above methods are essentially the same as the following.</p>
     *
     * <pre>
     * moment().months(11).days(31).hours(23).minutes(59).seconds(59).milliseconds(999);
     * moment().days(31).hours(23).minutes(59).seconds(59).milliseconds(999);
     * moment().minutes(59).seconds(59).milliseconds(999);
     * </pre>
     *
     * @param calendarField unit of time whose end time will be set to this moment instance.
     * @return this Moment instance for chainability.
     */
    public Moment endOf(int calendarField) {
        if (calendarField == Calendar.MILLISECOND) {
            return this;
        }

        return this.startOf(calendarField).add(1, calendarField).subtract(1, Calendar.MILLISECOND);
    }

    /**
     * Returns the String representation of this moment instance in given date pattern.
     * The pattern must obey java.text.SimpleDateFormat's rules.
     * <p>
     *
     * <pre>
     * moment().format("yyyy/MM/dd HH:mm:ss.SSS");
     * </pre>
     *
     * @param pattern the pattern which will be used to format the date
     * @return the String representation of this moment instance in given date pattern.
     */
    public String format(String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.format(calendar.getTime());
        } catch (Exception e) {
            throw new MomentException("Parse error occurred while formatting [" + calendar + "] with SimpleDateFormat [" + pattern + "]", e);
        }
    }

    /**
     * Returns a Date object which is created by using date and time of this moment instance
     *
     * @return a Date object which is created by using date and time of this moment instance
     * @see java.util.Date
     * @see java.util.Calendar#getTime()
     */
    public Date toDate() {
        return this.calendar.getTime();
    }

    /**
     * Returns a Calendar object which is created by using date and time of this moment instance
     *
     * @return a Calendar object which is created by using date and time of this moment instance
     * @see java.util.Calendar
     */
    public Calendar toCalendar() {
        return (Calendar) this.calendar.clone();
    }

    /**
     * @return the array of values
     */
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

    /**
     * Returns this Moment's time value in milliseconds.
     *
     * @return the time as UTC milliseconds from the epoch.
     * @see java.util.Date#getTime()
     * @see java.util.Calendar#getTimeInMillis()
     */
    public long valueOf() {
        return this.calendar.getTimeInMillis();
    }

    /**
     * Returns this Moment's time value in seconds.
     * <p>
     * Simply divides milliseconds to 1000.
     *
     * @return the time as UTC seconds from the epoch.
     * @see java.util.Date#getTime()
     * @see java.util.Calendar#getTimeInMillis()
     */
    public long unix() {
        return valueOf() / 1000;
    }

    /**
     * Creates a new Moment instance by copying this Moment instance's time value.
     *
     * @return a new Moment instance which is created by copying this Moment instance's time value.
     * @see Moment#moment(Moment)
     */
    @Override
    public Moment clone() {
        return new Moment((Calendar) this.calendar.clone());
    }

    /**
     * Returns true if this moment instance is before the specified moment instance
     *
     * @param moment moment to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is strictly earlier than the instant represented by the given moment parameter;
     * <code>false</code> otherwise.
     * @see java.util.Date#before(Date)
     */
    public boolean isBefore(Moment moment) {
        return isBefore(moment.valueOf());
    }

    /**
     * Returns true if this moment instance is before the specified date instance
     *
     * @param date date to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is strictly earlier than the instant represented by the given date parameter;
     * <code>false</code> otherwise.
     * @see java.util.Date#before(Date)
     */
    public boolean isBefore(Date date) {
        return isBefore(date.getTime());
    }

    /**
     * Returns true if this moment instance is before the specified calendar instance
     *
     * @param calendar calendar to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is strictly earlier than the instant represented by the given calendar parameter;
     * <code>false</code> otherwise.
     * @see java.util.Date#before(Date)
     */
    public boolean isBefore(Calendar calendar) {
        return isBefore(calendar.getTimeInMillis());
    }

    /**
     * Returns true if this moment instance is before the specified time as UTC milliseconds from the epoch.
     *
     * @param milliseconds time as UTC milliseconds from the epoch to compare with this moment instance.
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is strictly earlier than the instant represented by the given long parameter;
     * <code>false</code> otherwise.
     * @see java.util.Date#before(Date)
     */
    public boolean isBefore(long milliseconds) {
        return this.valueOf() < milliseconds;
    }

    /**
     * Returns true if this moment instance is before the specified moment instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param moment        moment instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is before the specified moment instance with the given precision.
     * @see java.util.Date#before(Date)
     */
    public boolean isBefore(Moment moment, int calendarField) {
        return isBefore(moment.valueOf(), calendarField);
    }

    /**
     * Returns true if this moment instance is before the specified date instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param date          date instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is before the specified date instance with the given precision.
     * @see java.util.Date#before(Date)
     */
    public boolean isBefore(Date date, int calendarField) {
        return isBefore(date.getTime(), calendarField);
    }

    /**
     * Returns true if this moment instance is before the specified calendar instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param calendar      calendar instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is before the specified calendar instance with the given precision.
     * @see java.util.Date#before(Date)
     */
    public boolean isBefore(Calendar calendar, int calendarField) {
        return isBefore(calendar.getTime(), calendarField);
    }

    /**
     * Returns true if this moment instance is before the specified time as UTC milliseconds from the epoch with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param milliseconds  time as UTC milliseconds from the epoch to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is before the specified time as UTC milliseconds from the epoch with the given precision.
     * @see java.util.Date#before(Date)
     */
    public boolean isBefore(long milliseconds, int calendarField) {
        return this.clone().endOf(calendarField).isBefore(milliseconds);
    }

    /**
     * Returns true if this moment instance is after the specified moment instance
     *
     * @param moment moment to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is strictly later than the instant represented by the given moment parameter;
     * <code>false</code> otherwise.
     * @see java.util.Date#after(Date)
     */
    public boolean isAfter(Moment moment) {
        return isAfter(moment.valueOf());
    }

    /**
     * Returns true if this moment instance is after the specified date instance
     *
     * @param date date to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is strictly later than the instant represented by the given date parameter;
     * <code>false</code> otherwise.
     * @see java.util.Date#after(Date)
     */
    public boolean isAfter(Date date) {
        return isAfter(date.getTime());
    }

    /**
     * Returns true if this moment instance is after the specified calendar instance
     *
     * @param calendar calendar to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is strictly later than the instant represented by the given calendar parameter;
     * <code>false</code> otherwise.
     * @see java.util.Date#after(Date)
     */
    public boolean isAfter(Calendar calendar) {
        return isAfter(calendar.getTimeInMillis());
    }

    /**
     * Returns true if this moment instance is after the specified time as UTC milliseconds from the epoch
     *
     * @param milliseconds time as UTC milliseconds from the epoch to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is strictly later than the instant represented by the given time as UTC milliseconds from the epoch;
     * <code>false</code> otherwise.
     * @see java.util.Date#after(Date)
     */
    public boolean isAfter(long milliseconds) {
        return this.valueOf() > milliseconds;
    }

    /**
     * Returns true if this moment instance is after the specified moment instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param moment        moment instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is after the specified moment instance with the given precision.
     * @see java.util.Date#after(Date)
     */
    public boolean isAfter(Moment moment, int calendarField) {
        return isAfter(moment.valueOf(), calendarField);
    }

    /**
     * Returns true if this moment instance is after the specified date instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param date          date instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is after the specified date instance with the given precision.
     * @see java.util.Date#after(Date)
     */
    public boolean isAfter(Date date, int calendarField) {
        return isAfter(date.getTime(), calendarField);
    }

    /**
     * Returns true if this calendar instance is after the specified date instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param calendar      calendar instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is after the specified calendar instance with the given precision.
     * @see java.util.Date#after(Date)
     */
    public boolean isAfter(Calendar calendar, int calendarField) {
        return isAfter(calendar.getTimeInMillis(), calendarField);
    }

    /**
     * Returns true if this moment instance is after the specified time as UTC milliseconds from the epoch with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param milliseconds  time as UTC milliseconds from the epoch to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is after the specified time as UTC milliseconds from the epoch with the given precision.
     * @see java.util.Date#after(Date)
     */
    public boolean isAfter(long milliseconds, int calendarField) {
        return this.clone().startOf(calendarField).isAfter(milliseconds);
    }

    /**
     * Compares this object with the specified object for order. Returns a negative integer, zero, or a positive
     * integer as this moment instance is before, equal to, or after the specified moment instance.
     *
     * @param moment the moment instance to compare to this moment instance
     * @return a negative integer, zero, or a positive integer as this moment instance is before, equal to, or after the specified moment instance.
     * @see java.lang.Comparable#compareTo(Object)
     * @see Moment#isAfter(Moment)
     * @see Moment#isBefore(Moment)
     * @see Moment#isSame(Moment)
     */
    public int compareTo(Moment moment) {
        return Long.compare(this.valueOf(), moment.valueOf());
    }

    /**
     * Returns true if this moment instance is the same as the specified moment instance
     *
     * @param moment moment to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is strictly the same as the instant represented by the given moment parameter;
     * <code>false</code> otherwise.
     * @see java.util.Date#equals(Object)
     */
    public boolean isSame(Moment moment) {
        return isSame(moment.valueOf());
    }

    /**
     * Returns true if this moment instance is the same as the specified date instance
     *
     * @param date date to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is strictly the same as the instant represented by the given date parameter;
     * <code>false</code> otherwise.
     * @see java.util.Date#equals(Object)
     */
    public boolean isSame(Date date) {
        return isSame(date.getTime());
    }

    /**
     * Returns true if this moment instance is the same as the specified calendar instance
     *
     * @param calendar calendar to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is strictly the same as the instant represented by the given calendar parameter;
     * <code>false</code> otherwise.
     * @see java.util.Date#equals(Object)
     */
    public boolean isSame(Calendar calendar) {
        return isSame(calendar.getTimeInMillis());
    }

    /**
     * Returns true if this moment instance is the same as the specified time as UTC milliseconds from the epoch
     *
     * @param milliseconds time as UTC milliseconds from the epoch to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is strictly the same as the instant represented by the given time as UTC milliseconds from the epoch;
     * <code>false</code> otherwise.
     * @see java.util.Date#equals(Object)
     */
    public boolean isSame(long milliseconds) {
        return this.valueOf() == milliseconds;
    }

    /**
     * Returns true if this moment instance is the same as the specified moment instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param moment        moment instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is the same as the specified moment instance with the given precision.
     * @see java.util.Date#equals(Object)
     */
    public boolean isSame(Moment moment, int calendarField) {
        return this.clone().startOf(calendarField).isSame(moment.clone().startOf(calendarField));
    }

    /**
     * Returns true if this moment instance is the same as the specified date instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param date          date instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is the same as the specified date instance with the given precision.
     * @see java.util.Date#equals(Object)
     */
    public boolean isSame(Date date, int calendarField) {
        return this.clone().startOf(calendarField).isSame(moment(date).startOf(calendarField));
    }

    /**
     * Returns true if this moment instance is the same as the specified calendar instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param calendar      calendar instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is the same as the specified calendar instance with the given precision.
     * @see java.util.Date#equals(Object)
     */
    public boolean isSame(Calendar calendar, int calendarField) {
        return this.clone().startOf(calendarField).isSame(moment(calendar).startOf(calendarField));
    }

    /**
     * Returns true if this moment instance is the same as the specified time as UTC milliseconds from the epoch with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param milliseconds  time as UTC milliseconds from the epoch to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is the same as the specified time as UTC milliseconds from the epoch with the given precision.
     * @see java.util.Date#equals(Object)
     */
    public boolean isSame(long milliseconds, int calendarField) {
        return this.clone().startOf(calendarField).isSame(moment(milliseconds).startOf(calendarField));
    }

    /**
     * Returns true if this moment instance is before or the same as the specified moment instance
     *
     * @param moment moment to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is before or the same as the instant represented by the given moment parameter;
     * <code>false</code> otherwise.
     * @see Moment#isBefore(Moment)
     * @see Moment#isSame(Moment)
     */
    public boolean isSameOrBefore(Moment moment) {
        return isBefore(moment) || isSame(moment);
    }

    /**
     * Returns true if this moment instance is before or the same as the specified date instance
     *
     * @param date date to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is before or the same as the instant represented by the given date parameter;
     * <code>false</code> otherwise.
     * @see Moment#isBefore(Date)
     * @see Moment#isSame(Date)
     */
    public boolean isSameOrBefore(Date date) {
        return isBefore(date) || isSame(date);
    }

    /**
     * Returns true if this moment instance is before or the same as the specified calendar instance
     *
     * @param calendar calendar to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is before or the same as the instant represented by the given calendar parameter;
     * <code>false</code> otherwise.
     * @see Moment#isBefore(Calendar)
     * @see Moment#isSame(Calendar)
     */
    public boolean isSameOrBefore(Calendar calendar) {
        return isBefore(calendar) || isSame(calendar);
    }

    /**
     * Returns true if this moment instance is before or the same as the specified time as UTC milliseconds from the epoch
     *
     * @param milliseconds time as UTC milliseconds from the epoch to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is before or the same as the instant represented by the given time as UTC milliseconds from the epoch;
     * <code>false</code> otherwise.
     * @see Moment#isBefore(long)
     * @see Moment#isSame(long)
     */
    public boolean isSameOrBefore(long milliseconds) {
        return isBefore(milliseconds) || isSame(milliseconds);
    }

    /**
     * Returns true if this moment instance is before or the same as the specified moment instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param moment        moment instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is before or the same as the specified moment instance with the given precision.
     * @see Moment#isBefore(Moment, int)
     * @see Moment#isSame(Moment, int)
     */
    public boolean isSameOrBefore(Moment moment, int calendarField) {
        return isBefore(moment, calendarField) || isSame(moment, calendarField);
    }

    /**
     * Returns true if this moment instance is before or the same as the specified date instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param date          date instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is before or the same as the specified date instance with the given precision.
     * @see Moment#isBefore(Date, int)
     * @see Moment#isSame(Date, int)
     */
    public boolean isSameOrBefore(Date date, int calendarField) {
        return isBefore(date, calendarField) || isSame(date, calendarField);
    }

    /**
     * Returns true if this moment instance is before or the same as the specified calendar instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param calendar      calendar instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is before or the same as the specified calendar instance with the given precision.
     * @see Moment#isBefore(Calendar, int)
     * @see Moment#isSame(Calendar, int)
     */
    public boolean isSameOrBefore(Calendar calendar, int calendarField) {
        return isBefore(calendar, calendarField) || isSame(calendar, calendarField);
    }

    /**
     * Returns true if this moment instance is before or the same as the specified time as UTC milliseconds from the epoch with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param milliseconds  time as UTC milliseconds from the epoch to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is before or the same as the specified time as UTC milliseconds from the epoch with the given precision.
     * @see Moment#isBefore(long, int)
     * @see Moment#isSame(long, int)
     */
    public boolean isSameOrBefore(long milliseconds, int calendarField) {
        return isBefore(milliseconds, calendarField) || isSame(milliseconds, calendarField);
    }

    /**
     * Returns true if this moment instance is after or the same as the specified moment instance
     *
     * @param moment moment to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is after or the same as the instant represented by the given moment parameter;
     * <code>false</code> otherwise.
     * @see Moment#isAfter(Moment)
     * @see Moment#isSame(Moment)
     */
    public boolean isSameOrAfter(Moment moment) {
        return isAfter(moment) || isSame(moment);
    }

    /**
     * Returns true if this moment instance is after or the same as the specified date instance
     *
     * @param date date to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is after or the same as the instant represented by the given date parameter;
     * <code>false</code> otherwise.
     * @see Moment#isAfter(Date)
     * @see Moment#isSame(Date)
     */
    public boolean isSameOrAfter(Date date) {
        return isAfter(date) || isSame(date);
    }

    /**
     * Returns true if this moment instance is after or the same as the specified calendar instance
     *
     * @param calendar calendar to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is after or the same as the instant represented by the given calendar parameter;
     * <code>false</code> otherwise.
     * @see Moment#isAfter(Calendar)
     * @see Moment#isSame(Calendar)
     */
    public boolean isSameOrAfter(Calendar calendar) {
        return isAfter(calendar) || isSame(calendar);
    }

    /**
     * Returns true if this moment instance is after or the same as the specified time as UTC milliseconds from the epoch
     *
     * @param milliseconds time as UTC milliseconds from the epoch to check
     * @return <code>true</code> if and only if the instant of time represented by this <tt>Moment</tt>
     * object is after or the same as the instant represented by the given time as UTC milliseconds from the epoch;
     * <code>false</code> otherwise.
     * @see Moment#isAfter(long)
     * @see Moment#isSame(long)
     */
    public boolean isSameOrAfter(long milliseconds) {
        return isAfter(milliseconds) || isSame(milliseconds);
    }

    /**
     * Returns true if this moment instance is after or the same as the specified moment instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param moment        moment instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is after or the same as the specified moment instance with the given precision.
     * @see Moment#isAfter(Moment, int)
     * @see Moment#isSame(Moment, int)
     */
    public boolean isSameOrAfter(Moment moment, int calendarField) {
        return isAfter(moment, calendarField) || isSame(moment, calendarField);
    }

    /**
     * Returns true if this moment instance is after or the same as the specified date instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param date          date instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is after or the same as the specified date instance with the given precision.
     * @see Moment#isAfter(Date, int)
     * @see Moment#isSame(Date, int)
     */
    public boolean isSameOrAfter(Date date, int calendarField) {
        return isAfter(date, calendarField) || isSame(date, calendarField);
    }

    /**
     * Returns true if this moment instance is after or the same as the specified calendar instance with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param calendar      calendar instance to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is after or the same as the specified calendar instance with the given precision.
     * @see Moment#isAfter(Calendar, int)
     * @see Moment#isSame(Calendar, int)
     */
    public boolean isSameOrAfter(Calendar calendar, int calendarField) {
        return isAfter(calendar, calendarField) || isSame(calendar, calendarField);
    }

    /**
     * Returns true if this moment instance is after or the same as the specified time as UTC milliseconds from the epoch with the given precision.
     * The second parameter determines the precision, and not just a single value to check, using <code>Calendar.DATE</code> will check for year, month and day.
     *
     * @param milliseconds  time as UTC milliseconds from the epoch to compare with this moment instance.
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is after or the same as the specified time as UTC milliseconds from the epoch with the given precision.
     * @see Moment#isAfter(long, int)
     * @see Moment#isSame(long, int)
     */
    public boolean isSameOrAfter(long milliseconds, int calendarField) {
        return isAfter(milliseconds, calendarField) || isSame(milliseconds, calendarField);
    }

    /**
     * Returns true if this moment instance is between the given two moment instances.
     * <p>
     * A moment instance is between the two moment instances <code>from</code> and <code>to</code>
     * if and only if it is after <code>from</code> and before <code>to</code>.
     * <p>
     * <code>moment.isBetween(from, to) == moment.isAfter(from) &amp;&amp; moment.isBefore(to)</code>
     *
     * @param from moment instance to check if this moment instance is after
     * @param to   moment instance to check if this moment instance is before
     * @return true if this moment instance is between the given two moment instances.
     * @see Moment#isAfter(Moment)
     * @see Moment#isBefore(Moment)
     */
    public boolean isBetween(Moment from, Moment to) {
        return isAfter(from) && isBefore(to);
    }

    /**
     * Returns true if this moment instance is between the given two date instances.
     * <p>
     * A moment instance is between the two date instances <code>from</code> and <code>to</code>
     * if and only if it is after <code>from</code> and before <code>to</code>.
     * <p>
     * <code>moment.isBetween(from, to) == moment.isAfter(from) &amp;&amp; moment.isBefore(to)</code>
     *
     * @param from date instance to check if this moment instance is after
     * @param to   date instance to check if this moment instance is before
     * @return true if this moment instance is between the given two date instances.
     * @see Moment#isAfter(Date)
     * @see Moment#isBefore(Date)
     */
    public boolean isBetween(Date from, Date to) {
        return isAfter(from) && isBefore(to);
    }

    /**
     * Returns true if this moment instance is between the given two calendar instances.
     * <p>
     * A moment instance is between the two calendar instances <code>from</code> and <code>to</code>
     * if and only if it is after <code>from</code> and before <code>to</code>.
     * <p>
     * <code>moment.isBetween(from, to) == moment.isAfter(from) &amp;&amp; moment.isBefore(to)</code>
     *
     * @param from calendar instance to check if this moment instance is after
     * @param to   calendar instance to check if this moment instance is before
     * @return true if this moment instance is between the given two calendar instances.
     * @see Moment#isAfter(Calendar)
     * @see Moment#isBefore(Calendar)
     */
    public boolean isBetween(Calendar from, Calendar to) {
        return isAfter(from) && isBefore(to);
    }

    /**
     * Returns true if this moment instance is between the given two times as UTC milliseconds from the epoch.
     * <p>
     * A moment instance is between the two milliseconds <code>from</code> and <code>to</code>
     * if and only if it is after <code>from</code> and before <code>to</code>.
     * <p>
     * <code>moment.isBetween(from, to) == moment.isAfter(from) &amp;&amp; moment.isBefore(to)</code>
     *
     * @param fromMillis time as UTC milliseconds from the epoch to check if this moment instance is after
     * @param toMillis   time as UTC milliseconds from the epoch to check if this moment instance is before
     * @return true if this moment instance is between the given two times as UTC milliseconds from the epoch.
     * @see Moment#isAfter(long)
     * @see Moment#isBefore(long)
     */
    public boolean isBetween(long fromMillis, long toMillis) {
        return isAfter(fromMillis) && isBefore(toMillis);
    }

    /**
     * Returns true if this moment instance is between the given two moment instances with the given precision.
     * <p>
     * A moment instance is between the two moment instances <code>from</code> and <code>to</code>
     * if and only if it is after <code>from</code> and before <code>to</code>.
     * <p>
     * <code>moment.isBetween(from, to, Calendar.DATE) == moment.isAfter(from, Calendar.DATE) &amp;&amp; moment.isBefore(to, Calendar.DATE)</code>
     *
     * @param from          moment instance to check if this moment instance is after
     * @param to            moment instance to check if this moment instance is before
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is between the given two moment instances with the given precision.
     * @see Moment#isAfter(Moment, int)
     * @see Moment#isBefore(Moment, int)
     */
    public boolean isBetween(Moment from, Moment to, int calendarField) {
        return isAfter(from, calendarField) && isBefore(to, calendarField);
    }

    /**
     * Returns true if this moment instance is between the given two date instances with the given precision.
     * <p>
     * A moment instance is between the two date instances <code>from</code> and <code>to</code>
     * if and only if it is after <code>from</code> and before <code>to</code>.
     * <p>
     * <code>moment.isBetween(from, to, Calendar.DATE) == moment.isAfter(from, Calendar.DATE) &amp;&amp; moment.isBefore(to, Calendar.DATE)</code>
     *
     * @param from          date instance to check if this moment instance is after
     * @param to            date instance to check if this moment instance is before
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is between the given two date instances with the given precision.
     * @see Moment#isAfter(Date, int)
     * @see Moment#isBefore(Date, int)
     */
    public boolean isBetween(Date from, Date to, int calendarField) {
        return isAfter(from, calendarField) && isBefore(to, calendarField);
    }

    /**
     * Returns true if this moment instance is between the given two calendar instances with the given precision.
     * <p>
     * A moment instance is between the two calendar instances <code>from</code> and <code>to</code>
     * if and only if it is after <code>from</code> and before <code>to</code>.
     * <p>
     * <code>moment.isBetween(from, to, Calendar.DATE) == moment.isAfter(from, Calendar.DATE) &amp;&amp; moment.isBefore(to, Calendar.DATE)</code>
     *
     * @param from          calendar instance to check if this moment instance is after
     * @param to            calendar instance to check if this moment instance is before
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is between the given two calendar instances with the given precision.
     * @see Moment#isAfter(Calendar, int)
     * @see Moment#isBefore(Calendar, int)
     */
    public boolean isBetween(Calendar from, Calendar to, int calendarField) {
        return isAfter(from, calendarField) && isBefore(to, calendarField);
    }

    /**
     * Returns true if this moment instance is between the given two times as UTC milliseconds from the epoch with the given precision.
     * <p>
     * A moment instance is between the two times as UTC milliseconds from the epoch <code>from</code> and <code>to</code>
     * if and only if it is after <code>from</code> and before <code>to</code>.
     * <p>
     * <code>moment.isBetween(from, to, Calendar.DATE) == moment.isAfter(from, Calendar.DATE) &amp;&amp; moment.isBefore(to, Calendar.DATE)</code>
     *
     * @param fromMillis    time as UTC milliseconds from the epoch to check if this moment instance is after
     * @param toMillis      time as UTC milliseconds from the epoch to check if this moment instance is before
     * @param calendarField the precision. For example, using <code>Calendar.DATE</code> will check for year, month and day.
     * @return true if this moment instance is between the given two times as UTC milliseconds from the epoch with the given precision.
     * @see Moment#isAfter(long, int)
     * @see Moment#isBefore(long, int)
     */
    public boolean isBetween(long fromMillis, long toMillis, int calendarField) {
        return isAfter(fromMillis, calendarField) && isBefore(toMillis, calendarField);
    }

    /**
     * Returns true if that year is a leap year, and false if it is not.
     *
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

    /**
     * Returns the maximum (most distant future) of the given moment instances.
     *
     * @param moments moment instances to compare in order to find the maximum (most distant future) one.
     * @return the maximum (most distant future) of the given moment instances.
     * If the given <code>moments</code> parameter is null or empty, returns the current date.
     * @see Moment#compareTo(Moment)
     * @see Moment#min(Moment...)
     */
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

    /**
     * Returns the minimum (most distant past) of the given moment instances.
     *
     * @param moments moment instances to compare in order to find the minimum (most distant past) one.
     * @return the minimum (most distant past) of the given moment instances.
     * If the given <code>moments</code> parameter is null or empty, returns the current date.
     * @see Moment#compareTo(Moment)
     * @see Moment#max(Moment...)
     */
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
     *
     * @param year year to control if it is leap or not
     * @return true if the given year is a leap year, and false if it is not.
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /**
     * Creates a moment instance for current time
     *
     * @return a new moment instance for current time
     */
    public static Moment moment() {
        return new Moment();
    }

    /**
     * Creates a moment instance for given date as string which will be parsed with given pattern
     *
     * @param dateString date as string to be parsed and encapsulated
     * @param pattern    pattern to be used to parse the dateString
     * @return a new moment instance for given date as string which will be parsed with given pattern
     */
    public static Moment moment(String dateString, String pattern) {
        return new Moment(dateString, pattern);
    }

    /**
     * Creates a moment instance by encapsulating the given calendar instance.
     * The given calendar is cloned. The operations on this moment have no effect on the given calendar instance.
     *
     * @param calendar calendar to be cloned and encapsulated
     * @return a new moment instance created by encapsulating the given calendar instance.
     */
    public static Moment moment(Calendar calendar) {
        return new Moment(calendar);
    }

    /**
     * Creates a moment instance by using the given date. The date parameter is copied.
     * The operations on this moment have no effect on the given date instance.
     *
     * @param date date to be used by this Moment instance.
     * @return a new moment instance created by using the given date. The date parameter is copied.
     */
    public static Moment moment(Date date) {
        return new Moment(date);
    }

    /**
     * Creates a moment instance by using the given time as UTC milliseconds from the epoch.
     *
     * @param timeInMillis time as UTC milliseconds from the epoch.
     * @return a new moment instance created by using the given time as UTC milliseconds from the epoch.
     */
    public static Moment moment(long timeInMillis) {
        return new Moment(timeInMillis);
    }

    /**
     * Creates a moment instance by using the given array of values.
     * <p>
     * The array must contain exactly 7 integers, which represents years, months, days, hours, minutes, seconds, and milliseconds.
     * <p>
     * For example, the following code can be used to create 31/01/2015 13:32:25.125
     *
     * <pre>
     * {@code
     * int[] arrayOfIntegers = new int[] {2015, 0, 31, 13, 32, 25, 125};
     * Moment moment = Moment.moment(arrayOfIntegers);
     * }
     * </pre>
     * <p>
     * Note that the first month in Java(January) is represented by 0.
     *
     * @param array array of values. The array must contain exactly 7 integers, which represents years, months, days, hours, minutes, seconds, and milliseconds.
     * @return a new moment instance created by using the given array of values.
     */
    public static Moment moment(int[] array) {
        return new Moment(array);
    }

    /**
     * Creates a new Moment instance by copying the given moment instance's time value.
     *
     * @param source the moment instance to be copied to the new moment instance.
     * @return a new Moment instance which is created by copying the given instance's time value.
     * @see Moment#clone()
     */
    public static Moment moment(Moment source) {
        return new Moment(source.valueOf());
    }

    @Override
    @SuppressWarnings("StringBufferReplaceableByString")
    public String toString() {
        return new StringBuilder("Moment{ ")
                .append(get(Calendar.YEAR)).append("/")
                .append(toTwoDigitsString(get(Calendar.MONTH) + 1)).append("/")
                .append(toTwoDigitsString(get(Calendar.DAY_OF_MONTH))).append(" ")
                .append(toTwoDigitsString(get(Calendar.HOUR_OF_DAY))).append(":")
                .append(toTwoDigitsString(get(Calendar.MINUTE))).append(":")
                .append(toTwoDigitsString(get(Calendar.SECOND))).append(".")
                .append(toThreeDigitsString(get(Calendar.MILLISECOND))).append(" }")
                .toString();
    }
}
