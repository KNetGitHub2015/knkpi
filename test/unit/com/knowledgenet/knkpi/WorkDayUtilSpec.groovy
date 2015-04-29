package com.knowledgenet.knkpi

import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import org.joda.time.MonthDay
import spock.lang.Specification
import static org.joda.time.DateTimeConstants.*

class WorkDayUtilSpec extends Specification {

    void "test current quarter Q1"() {
        expect:
        WorkDayUtil.Quarter.Q1 == WorkDayUtil.currentQuarter(new MonthDay(JANUARY, 1))
        WorkDayUtil.Quarter.Q1 == WorkDayUtil.currentQuarter(new MonthDay(JANUARY, 20))
        WorkDayUtil.Quarter.Q1 == WorkDayUtil.currentQuarter(new MonthDay(FEBRUARY, 29))
        WorkDayUtil.Quarter.Q1 == WorkDayUtil.currentQuarter(new MonthDay(MARCH, 15))
        WorkDayUtil.Quarter.Q1 == WorkDayUtil.currentQuarter(new MonthDay(MARCH, 31))
    }

    void "test current quarter Q2"() {
        expect:
        WorkDayUtil.Quarter.Q2 == WorkDayUtil.currentQuarter(new MonthDay(APRIL, 1))
        WorkDayUtil.Quarter.Q2 == WorkDayUtil.currentQuarter(new MonthDay(APRIL, 17))
        WorkDayUtil.Quarter.Q2 == WorkDayUtil.currentQuarter(new MonthDay(MAY, 5))
        WorkDayUtil.Quarter.Q2 == WorkDayUtil.currentQuarter(new MonthDay(JUNE, 9))
        WorkDayUtil.Quarter.Q2 == WorkDayUtil.currentQuarter(new MonthDay(JUNE, 30))
    }

    void "test current quarter Q3"() {
        expect:
        WorkDayUtil.Quarter.Q3 == WorkDayUtil.currentQuarter(new MonthDay(JULY, 1))
        WorkDayUtil.Quarter.Q3 == WorkDayUtil.currentQuarter(new MonthDay(JULY, 12))
        WorkDayUtil.Quarter.Q3 == WorkDayUtil.currentQuarter(new MonthDay(AUGUST, 26))
        WorkDayUtil.Quarter.Q3 == WorkDayUtil.currentQuarter(new MonthDay(SEPTEMBER, 1))
        WorkDayUtil.Quarter.Q3 == WorkDayUtil.currentQuarter(new MonthDay(SEPTEMBER, 30))
    }

    void "test current quarter Q4"() {
        expect:
        WorkDayUtil.Quarter.Q4 == WorkDayUtil.currentQuarter(new MonthDay(OCTOBER, 1))
        WorkDayUtil.Quarter.Q4 == WorkDayUtil.currentQuarter(new MonthDay(OCTOBER, 31))
        WorkDayUtil.Quarter.Q4 == WorkDayUtil.currentQuarter(new MonthDay(NOVEMBER, 5))
        WorkDayUtil.Quarter.Q4 == WorkDayUtil.currentQuarter(new MonthDay(DECEMBER, 25))
        WorkDayUtil.Quarter.Q4 == WorkDayUtil.currentQuarter(new MonthDay(DECEMBER, 31))
    }

    void "test current quarter begin Q1"() {
        int currentYear = new DateTime().year
        def quarterBegin = WorkDayUtil.currentQuarterBegin(simpleDate(currentYear, JANUARY, 20))
        expect:
        quarterBegin.year == currentYear
    }

    void "test total days in February"() {
        setNow(simpleDate(2015, 2, 15))
        int days = WorkDayUtil.totalDaysInMonth()
        expect:
        days == 20
    }

    void "test total days in April"() {
        setNow(simpleDate(2015, 4, 15))
        int days = WorkDayUtil.totalDaysInMonth()
        expect:
        days == 22
    }

    void "test total days in Q1"() {
        setNow(simpleDate(2015, 2, 1))
        int days = WorkDayUtil.totalDaysInQuarter()
        expect:
        days == 64
    }

    void "test total days in Q2"() {
        setNow(simpleDate(2015, 4, 25))
        int days = WorkDayUtil.totalDaysInQuarter()
        expect:
        days == 65
    }

    void "test total days in 2015"() {
        setNow(simpleDate(2015, 3, 14))
        int days = WorkDayUtil.totalDaysInYear()
        expect:
        days == 261
    }

    void "test day of month 4/28"() {
        setNow(simpleDate(2015, 4, 28))
        int result = WorkDayUtil.dayOfMonth()
        expect:
        result == 20
    }

    void "test day of quarter 3/14"() {
        setNow(simpleDate(2015, 3, 14))
        int result = WorkDayUtil.dayOfQuarter()
        expect:
        result == 52
    }

    void "test day of quarter 5/19"() {
        setNow(simpleDate(2015, 5, 19))
        int result = WorkDayUtil.dayOfQuarter()
        expect:
        result == 35
    }

    void "test day of year 4/1"() {
        setNow(simpleDate(2015, 4, 1))
        int result = WorkDayUtil.dayOfYear()
        expect:
        result == 65
    }

    void "test day of year 8/25"() {
        setNow(simpleDate(2015, 8, 25))
        int result = WorkDayUtil.dayOfYear()
        expect:
        result == 169
    }

    private static void setNow(DateTime input) {
        WorkDayUtil.metaClass.static.now = { return input }
    }

    private static DateTime simpleDate(int year, int month, int day) {
        return new DateTime(year, month, day, 0, 0)
    }
}
