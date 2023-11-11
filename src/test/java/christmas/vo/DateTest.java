package christmas.vo;

import static org.assertj.core.api.Assertions.assertThat;

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
        Assertions.assertThatThrownBy(() -> VisitDate.of(date))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("유효하지 않은 날짜입니다.");
    }

    @Test
    @DisplayName("금요일 토요일은 주말이다.")
    void isWeekend() {
        // given
        VisitDate friday = VisitDate.of(1);
        VisitDate saturday = VisitDate.of(2);
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
        List<VisitDate> weekdays = List.of(
            VisitDate.of(3),
            VisitDate.of(4),
            VisitDate.of(5),
            VisitDate.of(6),
            VisitDate.of(7)
        );
        // when
        List<Boolean> isWeekdays = weekdays.stream().map(VisitDate::isWeekdays).toList();
        // then
        assertThat(isWeekdays).containsOnly(true);
    }

    @Test
    @DisplayName("25일은 크리스마스이다.")
    void isChristmas() {
        // given
        VisitDate christmas = VisitDate.of(25);
        // when
        boolean isChristmas = christmas.isChristmas();
        // then
        assertThat(isChristmas).isTrue();
    }

    @Test
    @DisplayName("일요일인지 확인한다.")
    void isSunday() {
        // given
        VisitDate sunday = VisitDate.of(3);
        // when
        boolean isSunday = sunday.isSunday();
        // then
        assertThat(isSunday).isTrue();
    }
}