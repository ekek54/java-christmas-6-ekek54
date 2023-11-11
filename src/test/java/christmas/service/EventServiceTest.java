package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.mock.event.TestEvent;
import christmas.vo.VisitDate;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventServiceTest {
    private final EventService eventService = new EventService(List.of(new TestEvent()));
    private final EventService eventServiceWithMultiEvent = new EventService(List.of(new TestEvent(), new TestEvent()));
    private final Orders eventOrder = new Orders(List.of(
            new Order("양송이수프", 2),
            new Order("제로콜라", 1)
    ));

    private final Orders nonEventOrder = new Orders(List.of(
            new Order("양송이수프", 1),
            new Order("제로콜라", 1)
    ));


    @Test
    @DisplayName("총 주문 금액이 10000원 미만인 경우 할인이 적용되지 않는다.")
    void totalDiscountPrice() {
        // given
        VisitDate christmas = VisitDate.of(25);
        // when
        int discountPrice = eventService.totalEventPrice(nonEventOrder, christmas);
        // then
        assertThat(discountPrice).isEqualTo(0);
    }

    @Test
    @DisplayName("총 주문 금액이 10000원 이상인 경우 할인이 적용된다.")
    void totalDiscountPriceEventApplied() {
        // given
        Orders orders = new Orders(List.of(
                new Order("양송이수프", 2),
                new Order("제로콜라", 1)
        ));
        VisitDate visitDate = VisitDate.of(25);
        // when
        int discountPrice = eventService.totalEventPrice(orders, visitDate);
        // then
        assertThat(discountPrice).isNotZero();
    }

    @Test
    @DisplayName("총 주문 금액이 10000원 이상인 경우 사은품이 증정된다. - 사은품 목록 확인")
    void totalGiftMenusEventApplied() {
        // given
        VisitDate visitDate = VisitDate.of(25);
        // when
        Map<Menu, Integer> giftMenus = eventService.totalGiftMenus(eventOrder, visitDate);
        // then
        assertThat(giftMenus).isNotEmpty();
    }

    @Test
    @DisplayName("총 주문 금액이 10000원 이상인 경우 사은품이 증정된다. - 사은품 가격 확인")
    void totalGiftPriceEventApplied() {
        // given
        VisitDate visitDate = VisitDate.of(25);
        // when
        int giftPrice = eventService.totalGiftPrice(eventOrder, visitDate);
        // then
        assertThat(giftPrice).isNotZero();
    }

    @Test
    @DisplayName("적용 가능한 모든 이벤트를 적용한다.")
    void totalDiscountPriceWithMultiEvent() {
        // given
        VisitDate visitDate = VisitDate.of(25);

        // when
        int totalPriceSingleEvent = eventService.totalEventPrice(eventOrder, visitDate);
        int totalPriceDoubleEvent = eventServiceWithMultiEvent.totalEventPrice(eventOrder, visitDate);

        // then
        assertThat(totalPriceDoubleEvent == 2 * totalPriceSingleEvent).isTrue();
    }
}