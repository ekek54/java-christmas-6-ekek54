package christmas.domain.event;

import christmas.domain.order.Menu;
import christmas.domain.order.Orders;
import christmas.vo.VisitDate;
import java.util.Collections;
import java.util.Map;

public class SpecialEvent implements Event{
    private static final int SPECIAL_DISCOUNT_PRICE = 1000;
    private static final int NO_GIFT_PRICE = 0;
    private static final String name = "특별 할인";

    @Override
    public boolean isApplied(VisitDate visitDate, Orders orders) {
        return visitDate.isSunday() || visitDate.isChristmas();
    }

    @Override
    public int discountPrice(VisitDate visitDate, Orders orders) {
        return SPECIAL_DISCOUNT_PRICE;
    }

    @Override
    public int giftPrice(Orders orders) {
        return NO_GIFT_PRICE;
    }

    @Override
    public Map<Menu, Integer> giftMenus(Orders orders) {
        return Collections.unmodifiableMap(Map.of());
    }

    @Override
    public String getName() {
        return SpecialEvent.name;
    }
}
