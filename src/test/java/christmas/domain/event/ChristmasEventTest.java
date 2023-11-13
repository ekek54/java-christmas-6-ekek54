package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.vo.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasEventTest {
    private final ChristmasEvent christmasEvent = new ChristmasEvent();

    @Test
    @DisplayName("크리스마스가 끝나지 않았으면 이벤트를 적용한다.")
    void isApplied() {
        // given
        VisitDate christmas = VisitDate.of(25);
        // when
        boolean isApplied = christmasEvent.isApplied(christmas, null);
        // then
        assertThat(isApplied).isTrue();
    }

    @Test
    @DisplayName("크리스마스가 끝났으면 이벤트를 적용하지 않는다.")
    void isNotApplied() {
        // given
        VisitDate christmasOver = VisitDate.of(26);
        // when
        boolean isApplied = christmasEvent.isApplied(christmasOver, null);
        // then
        assertThat(isApplied).isFalse();
    }

    @Test
    @DisplayName("할인 금액은 1000원으로 시작한다.")
    void discountPrice() {
        // given
        VisitDate first = VisitDate.of(1);
        // when
        int discountPrice = christmasEvent.discountPrice(first, null);
        // then
        assertThat(discountPrice).isEqualTo(1000);
    }

    @Test
    @DisplayName("할인 금액은 100원씩 증가한다.")
    void discountPrice2() {
        // given
        VisitDate first = VisitDate.of(1);
        VisitDate second = VisitDate.of(2);
        // when
        int firstPrice = christmasEvent.discountPrice(first, null);
        int secondPrice = christmasEvent.discountPrice(second, null);
        // then
        assertThat(secondPrice - firstPrice).isEqualTo(100);
    }

    @Test
    @DisplayName("크리스마스에는 할인금액이 3400이 된다")
    void discountPrice3() {
        // given
        VisitDate christmas = VisitDate.of(25);
        // when
        int discountPrice = christmasEvent.discountPrice(christmas, null);
        // then
        assertThat(discountPrice).isEqualTo(3400);
    }

    @Test
    @DisplayName("사은품은 없다. - 사은품 목록 확인")
    void giftMenus() {
        // given
        // when
        int giftMenusSize = christmasEvent.giftMenus(null).size();
        // then
        assertThat(giftMenusSize).isEqualTo(0);
    }

    @Test
    @DisplayName("사은품은 없다. - 사은품 가격 확인")
    void giftPrice() {
        // given
        // when
        int giftPrice = christmasEvent.giftPrice(null);
        // then
        assertThat(giftPrice).isEqualTo(0);
    }

    @Test
    @DisplayName("이벤트 이름은 크리스마스 디데이 할인이다.")
    void getName() {
        // given
        // when
        String name = christmasEvent.getName();
        // then
        assertThat(name).isEqualTo("크리스마스 디데이 할인");
    }
}
