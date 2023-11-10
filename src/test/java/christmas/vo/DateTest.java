package christmas.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateTest {

    @Test
    @DisplayName("1~31 초과 날짜 생성시 예외가 발생한다.")
    void ofWithOutRangeDate() {
        // given
        int date = 32;

        // when
        // then
        Assertions.assertThatThrownBy(() -> Date.of(date))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("유효하지 않은 날짜입니다.");
    }

    @Test
    @DisplayName("금요일 토요일은 주말이다.")
    void isWeekend() {
        // given
        Date friday = Date.of(1);
        Date saturday = Date.of(2);
        // when
        boolean fridayIsWeekend = friday.isWeekend();
        boolean saturdayIsWeekend = saturday.isWeekend();
        // then
        assertThat(fridayIsWeekend).isTrue();
        assertThat(saturdayIsWeekend).isTrue();
    }

    @Test
    @DisplayName("일 월 화 수 목은 평일이다.")
    void isWeekdays() {
        // given
        List<Date> weekdays = List.of(
            Date.of(3),
            Date.of(4),
            Date.of(5),
            Date.of(6),
            Date.of(7)
        );
        // when
        List<Boolean> isWeekdays = weekdays.stream().map(Date::isWeekdays).toList();
        // then
        assertThat(isWeekdays).containsOnly(true);
    }

    @Test
    @DisplayName("25일은 크리스마스이다.")
    void isChristmas() {
        // given
        Date christmas = Date.of(25);
        // when
        boolean isChristmas = christmas.isChristmas();
        // then
        assertThat(isChristmas).isTrue();
    }

    @Test
    @DisplayName("일요일인지 확인한다.")
    void isSunday() {
        // given
        Date sunday = Date.of(3);
        // when
        boolean isSunday = sunday.isSunday();
        // then
        assertThat(isSunday).isTrue();
    }
}