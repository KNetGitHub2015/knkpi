package com.knowledgenet.knkpi

import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import org.joda.time.DateTimeZone
import org.joda.time.MonthDay

class WorkDayUtil {

    private static final def WEEKEND_DAYS = [DateTimeConstants.SATURDAY, DateTimeConstants.SUNDAY]
    private static final String AZ_TIMEZONE = 'America/Phoenix'
    private static final List QUARTERS = [
            [quarter: Quarter.Q1, begin: new MonthDay(DateTimeConstants.JANUARY, 1), end: new MonthDay(DateTimeConstants.MARCH, 31)],
            [quarter: Quarter.Q2, begin: new MonthDay(DateTimeConstants.APRIL, 1), end: new MonthDay(DateTimeConstants.JUNE, 30)],
            [quarter: Quarter.Q3, begin: new MonthDay(DateTimeConstants.JULY, 1), end: new MonthDay(DateTimeConstants.SEPTEMBER, 30)],
            [quarter: Quarter.Q4, begin: new MonthDay(DateTimeConstants.OCTOBER, 1), end: new MonthDay(DateTimeConstants.DECEMBER, 31)]
    ]

    public static DateTime now() {
        return new DateTime(DateTimeZone.forID(AZ_TIMEZONE))
    }

    public static int totalDaysInMonth() {
        def now = now()
        def begin = now.dayOfMonth().withMinimumValue()
        def end = now.dayOfMonth().withMaximumValue()

        return daysBetween(begin, end)
    }

    public static int totalDaysInQuarter() {
        def now = now()
        def begin = currentQuarterBegin(now).dayOfMonth().withMinimumValue()
        def end = currentQuarterEnd(now).dayOfMonth().withMaximumValue()

        return daysBetween(begin, end)
    }

    public static int totalDaysInYear() {
        def now = now()
        def begin = now.monthOfYear().withMinimumValue().dayOfMonth().withMinimumValue()
        def end = now.monthOfYear().withMaximumValue().dayOfMonth().withMaximumValue()

        return daysBetween(begin, end)
    }

    public static int dayOfMonth() {
        def now = now()
        def begin = now.dayOfMonth().withMinimumValue()

        return daysBetween(begin, now)
    }

    public static int dayOfQuarter() {
        def now = now()
        def begin = currentQuarterBegin(now)

        return daysBetween(new DateTime(begin), now)
    }

    public static int dayOfYear() {
        def now = now()
        def begin = new DateTime(now.year, DateTimeConstants.JANUARY, 1, 0, 0)

        return daysBetween(begin, now)
    }

    public static int daysBetween(DateTime begin, DateTime end) {
        int days = 0

        def current = begin
        while (current.isBefore(end) || current.equals(end)) {
            if (!WEEKEND_DAYS.contains(current.dayOfWeek)) {
                days++
            }
            current = current.plusDays(1)
        }

        return days
    }

    public static Quarter currentQuarter(MonthDay input) {
        return findCurrentQuarter(input)?.quarter
    }

    public static Quarter currentQuarter() {
        return currentQuarter(new MonthDay(DateTimeZone.forID(AZ_TIMEZONE)))
    }

    public static DateTime currentQuarterBegin(DateTime input) {
        def currentQuarter = findCurrentQuarter(new MonthDay(input))
        MonthDay begin = currentQuarter?.begin

        return new DateTime(input.year, begin.monthOfYear, begin.dayOfMonth, 0, 0)
    }

    public static DateTime currentQuarterEnd(DateTime input) {
        def currentQuarter = findCurrentQuarter(new MonthDay(input))
        MonthDay end = currentQuarter?.end

        return new DateTime(input.year, end.monthOfYear, end.dayOfMonth, 23, 59, 59, 999)
    }

    private static def findCurrentQuarter(MonthDay input) {
        def quarter = QUARTERS.find {
            boolean lowerBound = it.begin.equals(input) || it.begin.isBefore(input)
            boolean upperBound = it.end.equals(input) || input.isBefore(it.end)

            lowerBound && upperBound
        }

        if (!quarter) {
            throw new IllegalArgumentException("Unable to find quarter for bad date")
        }

        return quarter
    }

    public static enum Quarter {
        Q1,
        Q2,
        Q3,
        Q4
    }

}
