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
    
    // 10000원 이상 주문
    private final Orders eventOrder = new Orders(List.of(
            new Order("양송이수프", 2),
            new Order("제로콜라", 1)
    ));

    // 10000원 미만 주문
    private final Orders nonEventOrder = new Orders(List.of(
            new Order("양송이수프", 1),
            new Order("제로콜라", 1)
    ));

    private final VisitDate christmas = VisitDate.of(25);


    @Test
    @DisplayName("총 주문 금액이 10000원 미만인 경우 할인이 적용되지 않는다.")
    void totalDiscountPrice() {
        // given
        // when
        int discountPrice = eventService.totalEventPrice(christmas, nonEventOrder);
        // then
        assertThat(discountPrice).isEqualTo(0);
    }

    @Test
    @DisplayName("총 주문 금액이 10000원 이상인 경우 할인이 적용된다.")
    void totalDiscountPriceEventApplied() {
        // given
        // when
        int discountPrice = eventService.totalEventPrice(christmas, eventOrder);
        // then
        assertThat(discountPrice).isNotZero();
    }

    @Test
    @DisplayName("총 주문 금액이 10000원 이상인 경우 사은품이 증정된다. - 사은품 목록 확인")
    void totalGiftMenusEventApplied() {
        // given
        // when
        Map<Menu, Integer> giftMenus = eventService.totalGiftMenus(christmas, eventOrder);
        // then
        assertThat(giftMenus).isNotEmpty();
    }

    @Test
    @DisplayName("총 주문 금액이 10000원 미만인 경우 사은품이 증정되지 않는다. - 사은품 목록 확인")
    void totalGiftMenusEventNotApplied() {
        // given
        // when
        Map<Menu, Integer> giftMenus = eventService.totalGiftMenus(christmas, nonEventOrder);
        // then
        assertThat(giftMenus).isEmpty();
    }


    @Test
    @DisplayName("총 주문 금액이 10000원 이상인 경우 사은품이 증정된다. - 사은품 가격 확인")
    void totalGiftPriceEventApplied() {
        // given
        // when
        int giftPrice = eventService.totalGiftPrice(christmas, eventOrder);
        // then
        assertThat(giftPrice).isNotZero();
    }

    @Test
    @DisplayName("적용 가능한 모든 이벤트를 적용한다.")
    void totalDiscountPriceWithMultiEvent() {
        // given
        // when
        int totalPriceSingleEvent = eventService.totalEventPrice(christmas, eventOrder);
        int totalPriceDoubleEvent = eventServiceWithMultiEvent.totalEventPrice(christmas, eventOrder);

        // then
        assertThat(totalPriceDoubleEvent == 2 * totalPriceSingleEvent).isTrue();
    }

    @Test
    @DisplayName("적용된 이벤트 로그를 반환한다.")
    void appliedEvents() {
        // given
        // when
        Map<String, Integer> eventLogs = eventService.appliedEventLogs(christmas, eventOrder);
        // then
        assertThat(eventLogs).containsExactlyEntriesOf(Map.of("테스트 이벤트", 26000));
    }
}