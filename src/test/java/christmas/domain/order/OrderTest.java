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
    @DisplayName("주문의 카테고리를 확인한다.")
    void isDrink() {
        // given
        Order drinkOrder = new Order("제로콜라", 1);
        // when
        boolean isDrink = drinkOrder.isSameCategory(Category.DRINK);
        // then
        assertThat(isDrink).isTrue();
    }

    @Test
    @DisplayName("주문의 메뉴 이름을 확인한다.")
    void getMenuName() {
        // given
        Order drinkOrder = new Order("제로콜라", 1);
        // when
        String menuName = drinkOrder.getMenuName();
        // then
        assertThat(menuName).isEqualTo("제로콜라");
    }

    @Test
    @DisplayName("주문의 메뉴 개수를 확인한다.")
    void getCount() {
        // given
        Order drinkOrder = new Order("제로콜라", 1);
        // when
        int count = drinkOrder.getCount();
        // then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("다른 타입과 비교하면 false를 반환한다.")
    void equalsOtherType() {
        // given
        Order drinkOrder = new Order("제로콜라", 1);
        // when
        boolean equals = drinkOrder.equals("제로콜라");
        // then
        assertThat(equals).isFalse();
    }
}