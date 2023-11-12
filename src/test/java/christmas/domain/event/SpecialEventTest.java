package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.vo.VisitDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialEventTest {
    private final SpecialEvent specialEvent = new SpecialEvent();

    @Test
    @DisplayName("일요일 또는 크리스마스면 이벤트를 적용한다.")
    void isApplied() {
        // given
        List<VisitDate> sundayOrChristmas= List.of(
                VisitDate.of(3), // 일
                VisitDate.of(25) // 크리스마스
        );
        // when
        List<Boolean> isApplied = sundayOrChristmas.stream()
                .map(visitDate -> specialEvent.isApplied(visitDate, null)).toList();
        // then
        assertEquals(List.of(true, true), isApplied);
    }

    @Test
    @DisplayName("할인 금액은 1000원이다.")
    void discountPrice() {
        // given
        // when
        int discountPrice = specialEvent.discountPrice(null, null);
        // then
        assertEquals(1000, discountPrice);
    }

    @Test
    @DisplayName("사은품은 없다. - 사은품 목록 확인")
    void giftPrice() {
        // given
        // when
        int giftMenusSize = specialEvent.giftMenus(null).size();
        // then
        assertThat(giftMenusSize).isEqualTo(0);
    }

    @Test
    @DisplayName("사은품은 없다. - 사은품 가격 확인")
    void giftMenus() {
        // given
        // when
        int giftPrice = specialEvent.giftPrice(null);
        // then
        assertThat(giftPrice).isEqualTo(0);
    }
}