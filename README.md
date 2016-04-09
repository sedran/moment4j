moment4j — Parse, validate, manipulate, and display dates in Java. Inspired by [momentjs](http://momentjs.com/).
==================================================

[![Build Status](https://travis-ci.org/sedran/moment4j.svg?branch=master)](https://travis-ci.org/sedran/moment4j)
[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg?style=plastic)](https://github.com/sedran/moment4j/blob/master/LICENSE)
[![Coverity Scan](https://img.shields.io/coverity/scan/8366.svg)](https://scan.coverity.com/projects/sedran-moment4j)

Introduction
--------------------------------------

moment4j provides a simple date/time manipulation and query API for Java as momentjs does it in Javascript.
Its API design is as similar as possible to momentjs.
The `Moment` class is a wrapper class of `java.util.Calendar`.
Every operation is done over the `Calendar` instance.

Usage
--------------------------------------

To get a `Moment` instance, simply call `Moment.moment()` static method. 
This method returns a `Moment` instance which holds the current date.

```java
Moment moment = Moment.moment();
```

This method is developed in order to have a similar API to momentjs.
If you have a static import to `Moment.moment` method, you can use it like this:

```java
import static com.asosyalbebe.moment4j.Moment.moment;

Moment moment = moment();
```

Parse
--------------------------------------

You can construct Moment instances by using various type of data such as String, Long, Date, Calendar, or another Moment object.

### String

Moment uses `java.text.SimpleDateFormat` to format and parse dates to/from `String`s.
Any format which is acceptable by `SimpleDateFormat` is also acceptable by Moment.

```java
// Format:
Moment moment = moment(String dateString, String dateFormat);

// Example:
Moment moment = moment("2016-03-15 23:36:12.532", "yyyy-MM-dd HH:mm:ss.SSS");
```

[Learn More](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html) about patterns that `SimpleDateFormat` uses.

### Long

Similar to `new Date(Long milliseconds)`, you can create a `Moment` by passing a long value representing the number of milliseconds since the Unix Epoch (Jan 1 1970 12AM UTC).

```java
// Format:
Moment moment = moment(long timeInMilliseconds);

// Example:
long currentTimeMillis = System.currentTimeMillis();
Moment moment = moment(currentTimeMillis);
```

### Date

You can create a `Moment` with a pre-existing `java.util.Date` object.

```java
// Format:
Moment moment = moment(Date d);

// Example:
Date current = new Date();
Moment moment = moment(current);
```

This clones the `Date` object; further changes to the `Date` won't affect the `Moment`, and vice-versa.

### Calendar

You can create a `Moment` with a pre-existing `java.util.Calendar` object.

```java
// Format:
Moment moment = moment(Calendar cal);

// Example:
Calendar cal = Calendar.getInstance();
Moment moment = moment(cal);
```

This clones the `Calendar` object; further changes to the `Calendar` won't affect the `Moment`, and vice-versa.

### Moment

All moments are mutable. If you want a clone of a moment, you can do so explicitly or implicitly.


#### Clone a moment by using constructor

```java
// Format:
Moment moment = moment(Moment m);

// Example:
Moment m1 = moment();
Moment m2 = moment(m1);
```

#### Clone a moment by using `clone()` method

```java
// Format:
Moment moment = moment(Moment m);

// Example:
Moment m1 = moment();
Moment m2 = m1.clone();
```

Get+Set
--------------------------------------

Moment4j uses overloaded getters and setters.

Calling these methods without parameters acts as a getter, and calling them with a parameter acts as a setter.

**Note:** All of these methods mutate the original moment when used as setters.

### Millisecond

Gets or sets the milliseconds.

Accepts numbers from 0 to 999. If the range is exceeded, it will bubble up to the seconds.

```java
Moment moment = moment();

// Sets milliseconds to 542
moment.milliseconds(542);

// Returns 542
int ms = moment.milliseconds();
```

### Second

Gets or sets the seconds.

Accepts numbers from 0 to 59. If the range is exceeded, it will bubble up to the minutes.

```java
Moment moment = moment();

// Sets seconds to 25
moment.seconds(25);

// Returns 25
int secs = moment.seconds();
```

### Minute

Gets or sets the minutes.

Accepts numbers from 0 to 59. If the range is exceeded, it will bubble up to the hours.

```java
Moment moment = moment();

// Sets minutes to 25
moment.minutes(25);

// Returns 25
int minutes = moment.minutes();
```

### Hour

Gets or sets the hour.

Accepts numbers from 0 to 23. If the range is exceeded, it will bubble up to the day.

```java
Moment moment = moment();

// Sets hours to 21
moment.hours(21);

// Returns 21
int hours = moment.hours();
```

### Day of Month (Date of Month)

Gets or sets the day of the month.

Accepts numbers from 1 to 31. If the range is exceeded, it will bubble up to the months.

**Note:** `Moment#date` is for the date of the month, and `Moment#day` is for the day of the week.

**Note:** if you chain multiple actions to construct a date, you should start from a year, then a month, then a day etc. Otherwise you may get unexpected results, like when day=31 and current month has only 30 days (the same applies to native Calendar/Date manipulation), the returned date will be 1st of the following month.

**Bad:** `moment().date(day).month(month).year(year)`

**Good:** `moment().year(year).month(month).date(day)`

```java
Moment moment = moment();

// Sets day of month to 21
moment.dates(21);

// Returns 21
int dayOfMonth = moment.dates();
```

### Day of Week

Gets or sets the day of the week.

This method can be used to set the day of the week, with Sunday as 1 and Saturday as 7.

If the range is exceeded, it will bubble up to other weeks.

**Note:** `Moment#date` is for the date of the month, and `Moment#day` is for the day of the week.

```java
Moment moment = moment();

// Sets day of week to Sunday
// Note that calendar fields are integer
moment.days(Calendar.SUNDAY);

// Returns 1 == Calendar.SUNDAY
int dayOfWeek = moment.days();
```

### Day of Year

Gets or sets the day of the year.

Accepts numbers from 1 to 366. If the range is exceeded, it will bubble up to the years.

```java
Moment moment = moment();

// Sets day of year to 255
moment.dayOfYear(255);

// Returns 255
int dayOfYear = moment.dayOfYear();
```

### Month

Gets or sets the month.

Accepts numbers from 0 to 11. If the range is exceeded, it will bubble up to the year.

Note: Months are zero indexed, so January is month 0.

```java
Moment moment = moment();

// Sets month to February
// Note that calendar fields are integer
moment.months(Calendar.FEBRUARY);

// Returns 1 == Calendar.FEBRUARY
int month = moment.months();
```

### Year

Gets or sets the year.

```java
Moment moment = moment();

// Sets year to 2022
moment.years(Calendar.FEBRUARY);

// Returns 2022
int year = moment.years();
```

### Get

Gets the specified calendar field.

```java
Moment moment = moment();

// Sets month to February
moment.months(Calendar.FEBRUARY);

// Returns 1 == Calendar.FEBRUARY
int month = moment.get(Calendar.MONTH);
```

### Set

Gets the specified calendar field to the specified value.

```java
Moment moment = moment();

// Sets month to February
moment.set(Calendar.MONTH, Calendar.FEBRUARY);

// Returns 1 == Calendar.FEBRUARY
int month = moment.get(Calendar.MONTH);
```

Manipulate
--------------------------------------

Once you have a Moment, you may want to manipulate it in some way. 
There are a number of methods to help with this.

Moment4j uses the [fluent interface pattern](https://en.wikipedia.org/wiki/Fluent_interface),
also known as [method chaining](https://en.wikipedia.org/wiki/Method_chaining). 
This allows you to do crazy things like the following.

```java
moment().add(1, Calendar.DATE).subtract(1, Calendar.MONTH).years(2009).hours(0).minutes(0).seconds(0);
```

Note: It should be noted that moments are mutable. Calling any of the manipulation methods will change the original moment.

If you want to create a copy and manipulate it, you should use `moment#clone` before manipulating the moment.
[More info on cloning](#clone-a-moment-by-using-constructor).

### Add

Mutates the original moment by adding time.

This is a pretty robust function for adding time to an existing moment. To add time, pass the Calendar field of what time you want to add, and the amount you want to add.

```java
moment().add(5, Calendar.MINUTE);
moment().add(3, Calendar.HOUR_OF_DAY);
moment().add(7, Calendar.DATE);
moment().add(6, Calendar.MONTH);
moment().add(1, Calendar.YEAR);
```

### Subtract

Mutates the original moment by subtracting time.

This is exactly the same as `moment#add`, only instead of adding time, it subtracts time.

```java
moment().subtract(5, Calendar.MINUTE);
moment().subtract(3, Calendar.HOUR_OF_DAY);
moment().subtract(7, Calendar.DATE);
moment().subtract(6, Calendar.MONTH);
moment().subtract(1, Calendar.YEAR);
```

### Start of Time

Mutates the original moment by setting it to the start of a unit of time.

```java
moment().startOf(Calendar.YEAR);          // set to January 1st, 12:00 am this year
moment().startOf(Calendar.MONTH);         // set to the first of this month, 12:00 am
moment().startOf(Calendar.WEEK_OF_YEAR);  // set to the first day of this week, 12:00 am
moment().startOf(Calendar.DATE);          // set to 12:00 am today
moment().startOf(Calendar.HOUR);          // set to now, but with 0 mins, 0 secs, and 0 ms
moment().startOf(Calendar.MINUTE);        // set to now, but with 0 seconds and 0 milliseconds
moment().startOf(Calendar.SECOND);        // same as moment().milliseconds(0);
```

These shortcuts are essentially the same as the following.

```java
moment().startOf(Calendar.YEAR);
moment().months(0).dates(1).hours(0).minutes(0).seconds(0).milliseconds(0);
```

```java
moment().startOf(Calendar.HOUR);
moment().minutes(0).seconds(0).milliseconds(0);
```

### End of Time

Mutates the original moment by setting it to the end of a unit of time.

This is the same as `moment#startOf`, only instead of setting to the start of a unit of time, it sets to the end of a unit of time.

```java
moment().endOf(Calendar.YEAR); // set the moment to 12-31 23:59:59.999 this year
```

Display
--------------------------------------

Once parsing and manipulation are done, you need some way to display the moment.

### Format

This is the most robust display option. It takes a string of tokens and replaces them with their corresponding values.

Moment uses `java.text.SimpleDateFormat` to format and parse dates to/from `String`s.
Any format which is acceptable by `SimpleDateFormat` is also acceptable by Moment.

```java
Moment moment = moment().years(2038).months(3).dates(21).hours(22).minutes(12).seconds(32).milliseconds(321);

String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
String expectedResult = "2038-04-21 22:12:32.321";

Assert.assertEquals(expectedResult, moment.format(dateFormat));
```

[Learn More](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html) about patterns that `SimpleDateFormat` uses.

### As `java.util.Calendar`

To get the native Calendar object that Moment4j wraps, use moment#toCalendar.

This will return a clone of the Calendar that the moment uses, so any changes to that Calendar will not cause the moment to change.

```java
Calendar cal = moment().toCalendar();
```

### As `java.util.Date`

To get a Date object, use moment#toDate.

This will return a Date object returned by `Calendar#getTime` method. 
Modifications on the returned Date will not affect the original moment object.

```java
Date date = moment().toDate();
```

### As Unix Timestamp (Milliseconds)

`moment#valueOf` simply outputs the number of milliseconds since the Unix Epoch, just like `Date#getTime` or `Calendar.getTimeInMillis`.

```java
long time = moment(1318874398806).valueOf(); // Returns 1318874398806
```

To get a Unix timestamp (the number of seconds since the epoch) from a Moment, use `moment#unix`.

### As Unix Timestamp (Seconds)

`moment#unix` simply outputs the number of seconds since the Unix Epoch.

```java
moment(1318874398806).unix(); // 1318874398
```

This value is floored to the nearest second, and does not include a milliseconds component.


Query
--------------------------------------

### Is Before

Check if a moment is before another moment.

```java
Moment moment = moment(System.currentTimeMillis());
Moment futureMoment = moment(System.currentTimeMillis() + 1000);
moment.isBefore(futureMoment); // Returns true
```

The parameter of `moment#isBefore` method can be the following types.

+ Moment (com.asosyalbebe.moment4j.Moment)
+ Date (java.util.Date)
+ Calendar (java.util.Calendar)
+ long (the number of milliseconds since the Unix Epoch)

```java
moment().subtract(1, Calendar.DATE).isBefore(moment());                   // Returns true
moment().subtract(1, Calendar.DATE).isBefore(new Date());                 // Returns true
moment().subtract(1, Calendar.DATE).isBefore(Calendar.getInstance());     // Returns true
moment().subtract(1, Calendar.DATE).isBefore(System.currentTimeMillis()); // Returns true
```

If you want to limit the granularity to a unit other than milliseconds, pass the units as the `Calendar.SECOND` parameter.

As the second parameter determines the precision, and not just a single value to check, using `Calendar.DATE` will check for year, month and day.

```java
String pattern = "yyyy-MM-dd";
moment("2010-10-20", pattern).isBefore(moment("2010-12-31", pattern), Calendar.YEAR); // false
moment("2010-10-20", pattern).isBefore(moment("2011-01-01", pattern), Calendar.YEAR); // true
```

### Is Same

Check if a moment is the same as another moment.

```java
String pattern = "yyyy-MM-dd";
moment("2010-10-20", pattern).isSame(moment("2010-10-20", pattern)); // true
```

The parameter of `moment#isSame` method can be the following types.

+ Moment (com.asosyalbebe.moment4j.Moment)
+ Date (java.util.Date)
+ Calendar (java.util.Calendar)
+ long (the number of milliseconds since the Unix Epoch)

```java
long milliseconds = System.currentTimeMillis();

Calendar calendarInstance = Calendar.getInstance();
calendarInstance.setTimeInMillis(milliseconds);

moment(milliseconds).isSame(moment(milliseconds));   // Returns true
moment(milliseconds).isSame(new Date(milliseconds)); // Returns true
moment(milliseconds).isSame(calendarInstance);       // Returns true
moment(milliseconds).isSame(milliseconds);           // Returns true
```

If you want to limit the granularity to a unit other than milliseconds, pass it as the `Calendar.SECOND` parameter.

```java
String pattern = "yyyy-MM-dd";
moment("2010-10-20", pattern).isSame(moment("2009-12-31", pattern), Calendar.YEAR);  // false
moment("2010-10-20", pattern).isSame(moment("2010-01-01", pattern), Calendar.YEAR);  // true
moment("2010-10-20", pattern).isSame(moment("2010-12-31", pattern), Calendar.YEAR);  // true
moment("2010-10-20", pattern).isSame(moment("2011-01-01", pattern), Calendar.YEAR);  // false
```

When including a second parameter, it will match all units equal or larger. 
Passing in month will check month and year. Passing in `Calendar.DATE` will check day, month, and year.

```java
String pattern = "yyyy-MM-dd";
moment("2010-01-01", pattern).isSame(moment("2011-01-01", pattern), Calendar.MONTH); // false, different year
moment("2010-01-01", pattern).isSame(moment("2010-02-01", pattern), Calendar.DATE);  // false, different month
```


### Is After

Check if a moment is after another moment.

```java
String pattern = "yyyy-MM-dd";
moment("2010-10-20", pattern).isAfter(moment("2010-10-19", pattern)); // true
```

The parameter of `moment#isAfter` method can be the following types.

+ Moment (com.asosyalbebe.moment4j.Moment)
+ Date (java.util.Date)
+ Calendar (java.util.Calendar)
+ long (the number of milliseconds since the Unix Epoch)

```java
moment().add(1, Calendar.DATE).isAfter(moment());                   // Returns true
moment().add(1, Calendar.DATE).isAfter(new Date());                 // Returns true
moment().add(1, Calendar.DATE).isAfter(Calendar.getInstance());     // Returns true
moment().add(1, Calendar.DATE).isAfter(System.currentTimeMillis()); // Returns true
```

If you want to limit the granularity to a unit other than milliseconds, pass the units as the `Calendar.SECOND` parameter.

As the second parameter determines the precision, and not just a single value to check, using `Calendar.DATE` will check for year, month and day.

```java
String pattern = "yyyy-MM-dd";
moment("2010-10-20", pattern).isAfter(moment("2010-01-01", pattern), Calendar.YEAR); // false
moment("2010-10-20", pattern).isAfter(moment("2009-12-31", pattern), Calendar.YEAR); // true
```

### Is Same or Before

Check if a moment is before or the same as another moment.

```java
String pattern = "yyyy-MM-dd";
moment("2010-10-20", pattern).isSameOrBefore(moment("2010-10-21", pattern));  // true
moment("2010-10-20", pattern).isSameOrBefore(moment("2010-10-20", pattern));  // true
moment("2010-10-20", pattern).isSameOrBefore(moment("2010-10-19", pattern));  // false
```

Like `moment#isSame` and `moment#isBefore`, `moment#isSameOrBefore` supports `Moment`, `Date`, `Calendar`, and `long` parameter types.

If you want to limit the granularity to a unit other than milliseconds, pass the units as the `Calendar.SECOND` parameter.

As the second parameter determines the precision, and not just a single value to check, using `Calendar.DATE` will check for year, month and day.

```java
String pattern = "yyyy-MM-dd";
moment("2010-10-20", pattern).isSameOrBefore(moment("2009-12-31", pattern), Calendar.YEAR); // false
moment("2010-10-20", pattern).isSameOrBefore(moment("2010-12-31", pattern), Calendar.YEAR); // true
moment("2010-10-20", pattern).isSameOrBefore(moment("2011-01-01", pattern), Calendar.YEAR); // true
```

### Is Same or After

Check if a moment is after or the same as another moment.

```java
String pattern = "yyyy-MM-dd";
moment("2010-10-20", pattern).isSameOrAfter(moment("2010-10-19", pattern)); // true
moment("2010-10-20", pattern).isSameOrAfter(moment("2010-10-20", pattern)); // true
moment("2010-10-20", pattern).isSameOrAfter(moment("2010-10-21", pattern)); // false
```

Like `moment#isSame` and `moment#isAfter`, `moment#isSameOrAfter` supports `Moment`, `Date`, `Calendar`, and `long` parameter types.

If you want to limit the granularity to a unit other than milliseconds, pass the units as the `Calendar.SECOND` parameter.

As the second parameter determines the precision, and not just a single value to check, using `Calendar.DATE` will check for year, month and day.

```java
String pattern = "yyyy-MM-dd";
moment("2010-10-20", pattern).isSameOrAfter(moment("2011-12-31", pattern), Calendar.YEAR); // false
moment("2010-10-20", pattern).isSameOrAfter(moment("2010-01-01", pattern), Calendar.YEAR); // true
moment("2010-10-20", pattern).isSameOrAfter(moment("2009-12-31", pattern), Calendar.YEAR); // true
```

### Is Between

Check if a moment is between two other moments, optionally looking at unit scale (minutes, hours, days, etc). The match is exclusive.

```java
String pattern = "yyyy-MM-dd";

moment("2010-10-20", pattern).isBetween(moment("2010-10-19", pattern), moment("2010-10-25", pattern)); // true
```

Like `moment#isBefore` and `moment#isAfter`, `moment#isBetween` supports `Moment`, `Date`, `Calendar`, and `long` parameter types.

If you want to limit the granularity to a unit other than milliseconds, pass the units as the `Calendar.SECOND` parameter.

```java
String pattern = "yyyy-MM-dd";

moment("2010-10-20", pattern).isBetween(moment("2010-01-01", pattern), moment("2012-01-01", pattern), Calendar.YEAR); // false
moment("2010-10-20", pattern).isBetween(moment("2009-12-31", pattern), moment("2012-01-01", pattern), Calendar.YEAR); // true
```

### Is Leap Year

`moment#isLeapYear` returns true if that year is a [leap year](https://en.wikipedia.org/wiki/Leap_year), and false if it is not.

```java
moment().years(2000).isLeapYear(); // true
moment().years(2001).isLeapYear(); // false
moment().years(2100).isLeapYear(); // false
```


Static Utility Methods
--------------------------------------

### Max

Returns the maximum (most distant future) of the given moment instances.

```java
String pattern = "yyyy-MM-dd";

// Returns moment("2010-10-22", pattern)
Moment.max(
    moment("2010-10-20", pattern), 
    moment("2010-10-21", pattern), 
    moment("2010-10-22", pattern)
);
```

`Moment#max` can take any number of arguments. 
If only one parameter is passed, returns that parameter.
If no parameter is passed, returns current date.


### Min

Returns the minimum (most distant past) of the given moment instances.

```java
String pattern = "yyyy-MM-dd";

// Returns moment("2010-10-20", pattern)
Moment.max(
    moment("2010-10-20", pattern), 
    moment("2010-10-21", pattern), 
    moment("2010-10-22", pattern)
);
```

`Moment#min` can take any number of arguments. 
If only one parameter is passed, returns that parameter.
If no parameter is passed, returns current date.


### Static Is Leap Method

`isLeapYear(int year)` returns true if the given year is a [leap year](https://en.wikipedia.org/wiki/Leap_year), and false if it is not.

```java
Moment.isLeapYear(2000); // true
Moment.isLeapYear(2001); // false
Moment.isLeapYear(2002); // false
```


### Comparable Interface and compareTo Method

Moment class implements `Comparable<Moment>` interface. Thus, it can be added to TreeSet implementations without writing a comparator.

`Moment#compareTo` method simply calls `Long.compare` method with Unix Timestamp values of two moment instances.

```java
public int compareTo(Moment o) {
    return Long.compare(this.valueOf(), o.valueOf());
}
```