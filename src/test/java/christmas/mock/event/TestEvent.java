package christmas.mock.event;

import christmas.domain.event.Event;
import christmas.domain.order.Menu;
import christmas.domain.order.Orders;
import christmas.vo.VisitDate;
import java.util.Collections;
import java.util.Map;

/**
 * TestEvent는 크리스마스에 적용되는 이벤트이다.
 * TestEvent의 할인 금액은 1000원이다.
 * TestEvent의 사은품 메뉴는 샴페인 1개이다.
 */
public class TestEvent implements Event {

    private final Map<Menu, Integer> giftMenus = Map.of(Menu.of("샴페인"), 1);
    @Override
    public boolean isApplied(VisitDate visitDate, Orders orders) {
        return visitDate.isChristmas();
    }

    @Override
    public int discountPrice(Orders orders) {
        return 1000;
    }

    @Override
    public int giftPrice(Orders orders) {
        return Menu.of("샴페인").calculatePrice(1);
    }

    @Override
    public Map<Menu, Integer> giftMenus(Orders orders) {
        return Collections.unmodifiableMap(giftMenus);
    }
}
