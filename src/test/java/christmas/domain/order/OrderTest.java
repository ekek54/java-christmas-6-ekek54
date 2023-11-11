package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    @Test
    @DisplayName("주문 수량이 1개 미만이면 예외가 발생한다.")
    void validateCount() {
        // given
        int count = 0;
        // when
        // then
        Assertions.assertThatThrownBy(() -> new Order("양송이수프", count))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("주문 수량은 1개 이상이어야 합니다.");
    }

    @Test
    @DisplayName("주문 수량이 1개 이상이면 주문을 생성한다.")
    void createOrder() {
        // given
        int count = 1;
        // when
        Order order = new Order("양송이수프", count);
        // then
        assertThat(order).isNotNull();
    }

    @Test
    @DisplayName("주문 수량을 합산한다.")
    void sumCount() {
        // given
        int count = 1;
        Order order = new Order("양송이수프", count);
        // when
        int sum = order.sumCount(10);
        // then
        assertThat(sum).isEqualTo(11);
    }

    @Test
    @DisplayName("음료 주문이면 true를 반환한다.")
    void isDrink() {
        // given
        Order drinkOrder = new Order("제로콜라", 1);
        // when
        boolean isDrink = drinkOrder.isDrink();
        // then
        assertThat(isDrink).isTrue();
    }
}