package christmas.domain.event;

import christmas.domain.order.Menu;
import christmas.domain.order.Orders;
import christmas.vo.VisitDate;
import java.util.Map;

public class ChristmasEvent implements Event{
    private static final int DISCOUNT_PRICE_PER_DAY = 100;
    private static final int MAX_DISCOUNT_PRICE = 3400;
    private static final int NO_GIFT_PRICE = 0;
    private static final String name = "크리스마스 디데이 할인";

    private final VisitDate christmas = VisitDate.of(25);

    @Override
    public boolean isApplied(VisitDate visitDate, Orders orders) {
        return visitDate.remainDaysUntil(christmas) >= 0;
    }

    @Override
    public int discountPrice(VisitDate visitDate, Orders orders) {
        return MAX_DISCOUNT_PRICE - visitDate.remainDaysUntil(christmas) * DISCOUNT_PRICE_PER_DAY;
    }

    @Override
    public int giftPrice(Orders orders) {
        return NO_GIFT_PRICE;
    }

    @Override
    public Map<Menu, Integer> giftMenus(Orders orders) {
        return Map.of();
    }

    @Override
    public String getName() {
        return ChristmasEvent.name;
    }
}
