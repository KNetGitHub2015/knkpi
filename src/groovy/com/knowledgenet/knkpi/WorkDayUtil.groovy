package com.knowledgenet.knkpi

import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import org.joda.time.DateTimeZone

class WorkDayUtil {

    private static final def WEEKEND_DAYS = [DateTimeConstants.SATURDAY, DateTimeConstants.SUNDAY]
    private static final String AZ_TIMEZONE = 'America/Phoenix'

    public static int totalDaysInMonth() {
        def now = new DateTime(DateTimeZone.forID(AZ_TIMEZONE))
        def begin = now.dayOfMonth().withMinimumValue()
        def end = now.dayOfMonth().withMaximumValue().plusDays(1)

        return daysBetween(begin, end)
    }

    public static int dayOfMonth() {
        def now = new DateTime(DateTimeZone.forID(AZ_TIMEZONE))
        def begin = now.dayOfMonth().withMinimumValue()

        return daysBetween(begin, now)
    }

    public static int daysBetween(DateTime begin, DateTime end) {
        int days = 0

        def current = begin
        while (current.isBefore(end)) {
            if (!WEEKEND_DAYS.contains(current.dayOfWeek)) {
                days++
            }
            current = current.plusDays(1)
        }

        return days
    }

}
