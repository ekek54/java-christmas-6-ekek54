package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.vo.VisitDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayEventTest {
    private final WeekdayEvent weekdayEvent = new WeekdayEvent();

    @Test
    @DisplayName("방문일이 평일이면 이벤트를 적용한다.")
    void isApplied() {
        // given
        List<VisitDate> weekdays = List.of(
                VisitDate.of(3), // 일
                VisitDate.of(4), // 월
                VisitDate.of(5), // 화
                VisitDate.of(6), // 수
                VisitDate.of(7)  // 목
        );
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 3)
        ));
        // when
        List<Boolean> isApplied = weekdays.stream()
                .map(weekday -> weekdayEvent.isApplied(weekday, orders)).toList();
        // then
        assertThat(isApplied).containsOnly(true);
    }

    @Test
    @DisplayName("방문일이 주말이면 이벤트를 적용하지 않는다.")
    void isNotApplied() {
        // given
        List<VisitDate> weekdays = List.of(
                VisitDate.of(1), // 금
                VisitDate.of(2)  // 토
        );
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 3)
        ));
        // when
        List<Boolean> isApplied = weekdays.stream()
                .map(weekday -> weekdayEvent.isApplied(weekday, orders)).toList();
        // then
        assertThat(isApplied).containsOnly(false);
    }


    @Test
    @DisplayName("할인 금액은 디저트 1개당 2023원이다.")
    void discountPrice() {
        // given
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 3),
                new Order("초코케이크", 2),
                new Order("아이스크림", 1)
        ));
        // when
        int discountPrice = weekdayEvent.discountPrice(orders);
        // then
        assertThat(discountPrice).isEqualTo(2023 * 3);
    }

    @Test
    @DisplayName("사은품은 없다. - 사은품 목록 확인")
    void noGiftMenus() {
        // given
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 3),
                new Order("초코케이크", 2),
                new Order("아이스크림", 1)
        ));
        // when
        int giftMenusSize = weekdayEvent.giftMenus(orders).size();
        // then
        assertThat(giftMenusSize).isEqualTo(0);
    }

    @Test
    @DisplayName("사은품은 없다. - 사은품 가격 확인")
    void noGiftPrice() {
        // given
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 3),
                new Order("초코케이크", 2),
                new Order("아이스크림", 1)
        ));
        // when
        int giftPrice = new WeekdayEvent().giftPrice(orders);
        // then
        assertThat(giftPrice).isEqualTo(0);
    }
}