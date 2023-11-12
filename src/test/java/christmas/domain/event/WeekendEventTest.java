package christmas.domain.event;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.vo.VisitDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekendEventTest {
    private final WeekendEvent weekendEvent = new WeekendEvent();

    @Test
    @DisplayName("방문일이 주말이면 이벤트를 적용한다.")
    void isApplied() {
        // given
        List<VisitDate> weekend = List.of(
                VisitDate.of(1), // 금
                VisitDate.of(2)  // 토
        );
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 3)
        ));
        // when
        List<Boolean> isApplies = weekend.stream().map(
                visitDate -> weekendEvent.isApplied(visitDate, orders)
        ).toList();
        // then
        assertThat(isApplies).containsOnly(true);
    }

    @Test
    @DisplayName("방문일이 평일이면 이벤트를 적용하지 않는다.")
    void isNotApplied() {
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
        List<Boolean> isApplies = weekdays.stream().map(
                visitDate -> weekendEvent.isApplied(visitDate, orders)
        ).toList();
        // then
        assertThat(isApplies).containsOnly(false);
    }

    @Test
    @DisplayName("할인 금액은 메인 1개당 2023원이다.")
    void discountPrice() {
        // given
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 3),
                new Order("초코케이크", 2),
                new Order("아이스크림", 1)
        ));
        // when
        int discountPrice = weekendEvent.discountPrice(orders);
        // then
        assertThat(discountPrice).isEqualTo(2023 * 3);
    }

    @Test
    @DisplayName("사은품은 없다. - 사은품 목록 확인")
    void giftPrice() {
        // given
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 3),
                new Order("초코케이크", 2),
                new Order("아이스크림", 1)
        ));
        // when
        int giftMenusSize = weekendEvent.giftMenus(orders).size();
        // then
        assertThat(giftMenusSize).isEqualTo(0);
    }

    @Test
    @DisplayName("사은품은 없다. - 사은품 가격 확인")
    void giftMenus() {
        // given
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 3),
                new Order("초코케이크", 2),
                new Order("아이스크림", 1)
        ));
        // when
        int giftPrice = weekendEvent.giftPrice(orders);
        // then
        assertThat(giftPrice).isEqualTo(0);
    }
}