package christmas.vo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Date {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int HOUR = 0;
    private static final int MINUTE = 0;
    private static final int FIRST_DATE = 1;
    private static final int LAST_DATE = 31;
    private static final String ERROR_MESSAGE = "유효하지 않은 날짜입니다.";
    private static final List<Date> dates = new ArrayList<>();
    private final int date;
    private final DayOfWeek dayOfWeek;

    static {
        for (int i = 1; i <= 31; i++) {
            dates.add(new Date(i));
        }
    }

    private Date(int date) {
        this.date = date;
        this.dayOfWeek = LocalDateTime.of(YEAR, MONTH, date, HOUR, MINUTE).getDayOfWeek();
    }

    public static Date of(int date) {
        validateDateInRange(date);
        return dates.get(date - 1);
    }

    private static void validateDateInRange(int date) {
        if (date < FIRST_DATE || date > LAST_DATE) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public boolean isWeekend() {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isWeekdays() {
        return !isWeekend();
    }

    public boolean isChristmas() {
        return date == 25;
    }

    public boolean isSunday() {
        return dayOfWeek == DayOfWeek.SUNDAY;
    }
}
