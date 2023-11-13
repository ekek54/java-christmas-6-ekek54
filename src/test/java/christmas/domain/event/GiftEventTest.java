package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.vo.VisitDate;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftEventTest {

    @Test
    @DisplayName("주문 금액이 120000원 이상이면 이벤트가 적용된다")
    void isApplied() {
        // given
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 3)
        ));
        VisitDate visitDate = VisitDate.of(1);
        // when
        boolean isApplied = new GiftEvent().isApplied(visitDate, orders);
        // then
        assertThat(isApplied).isTrue();
    }

    @Test
    @DisplayName("주문 금액이 120000원 미만이면 이벤트가 적용되지 않는다")
    void isNotApplied() {
        // given
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 2)
        ));
        VisitDate visitDate = VisitDate.of(1);
        // when
        boolean isApplied = new GiftEvent().isApplied(visitDate, orders);
        // then
        assertThat(isApplied).isFalse();
    }

    @Test
    @DisplayName("증정 이벤트의 할인 금액은 0원이다")
    void discountPrice() {
        // given
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 3)
        ));
        // when
        int discountPrice = new GiftEvent().discountPrice(null, orders);
        // then
        assertThat(discountPrice).isEqualTo(0);
    }

    @Test
    @DisplayName("증정 이벤트의 사은품 금액은 샴페인 1개 가격이다.")
    void giftPrice() {
        // given
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 3)
        ));
        // when
        int giftPrice = new GiftEvent().giftPrice(orders);
        // then
        assertThat(giftPrice).isEqualTo(Menu.of("샴페인").calculatePrice(1));
    }

    @Test
    @DisplayName("증정 이벤트의 사은품 메뉴는 샴페인 1개이다")
    void giftMenus() {
        // given
        Orders orders = new Orders(List.of(
                new Order("티본스테이크", 3)
        ));
        // when
        var giftMenus = new GiftEvent().giftMenus(orders);
        // then
        assertThat(giftMenus).containsExactlyEntriesOf(
                Map.of(
                        Menu.of("샴페인"), 1
                )
        );
    }

    @Test
    @DisplayName("이벤트 이름은 증정 이벤트이다")
    void name() {
        // given
        // when
        String name = new GiftEvent().getName();
        // then
        assertThat(name).isEqualTo("증정 이벤트");
    }
}