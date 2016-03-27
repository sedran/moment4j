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

```java
// Format:
Moment moment = moment(Moment m);

// Example:
Moment m1 = moment();
Moment m2 = moment(m1);
```
