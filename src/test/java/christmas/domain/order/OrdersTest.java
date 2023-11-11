package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdersTest {
    @Test
    @DisplayName("음료만 주문하면 예외가 발생한다.")
    void validateDrinkOrder() {
        // given
        List<Order> orders = List.of(
                new Order("제로콜라", 3),
                new Order("레드와인", 2),
                new Order("샴페인", 1));
        // when
        // then
        assertThatThrownBy(() -> new Orders(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료만 주문할 수 없습니다.");
    }

    @Test
    @DisplayName("주문 수량이 20개를 초과하면 예외가 발생한다.")
    void validateMaxOrderCount() {
        // given
        List<Order> orders = List.of(
                new Order("양송이수프", 10),
                new Order("레드와인", 10),
                new Order("샴페인", 1));
        // when
        // then
        assertThatThrownBy(() -> new Orders(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 수량은 20개 이하여야 합니다.");
    }

    @Test
    @DisplayName("중복된 메뉴 주문이 있으면 예외가 발생한다.")
    void validateOrderDuplicate() {
        // given
        List<Order> orders = List.of(
                new Order("양송이수프", 10),
                new Order("양송이수프", 3),
                new Order("샴페인", 1));
        // when
        // then
        assertThatThrownBy(() -> new Orders(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 주문이 존재합니다.");
    }

    @Test
    @DisplayName("주문 성공 테스트")
    void createOrders() {
        // given
        List<Order> orders = List.of(
                new Order("양송이수프", 10),
                new Order("레드와인", 5),
                new Order("샴페인", 5));
        // when
        Orders order = new Orders(orders);
        // then
        assertThat(order).isNotNull();
    }

    @Test
    @DisplayName("총 주문 금액 계산 테스트")
    void calculateTotalPrice() {
        // given
        /*
        양송이수프 10개 60,000원
        레드와인 5개 300,000원
        샴페인 5개 125,000원
        총 485,000원
        * */
        List<Order> orders = List.of(
                new Order("양송이수프", 10),
                new Order("레드와인", 5),
                new Order("샴페인", 5));
        Orders order = new Orders(orders);
        // when
        int totalPrice = order.calculateTotalPrice();
        // then
        assertThat(totalPrice).isEqualTo(485_000);
    }
}