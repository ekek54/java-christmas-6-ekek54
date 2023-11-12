package christmas.domain.event;

import christmas.domain.order.Menu;
import christmas.domain.order.Orders;
import christmas.vo.VisitDate;
import java.util.Collections;
import java.util.Map;

public class GiftEvent implements Event{
    private static final int EVENT_APPLIED_PRICE = 120_000;
    private static final int NO_DISCOUNT_PRICE = 0;

    private final Map<Menu, Integer> giftMenus = Map.of(Menu.of("샴페인"), 1);

    @Override
    public boolean isApplied(VisitDate visitDate, Orders orders) {
        return overEventAppliedPrice(orders);
    }

    private static boolean overEventAppliedPrice(Orders orders) {
        return orders.calculateTotalPrice() > EVENT_APPLIED_PRICE;
    }

    @Override
    public int discountPrice(Orders orders) {
        return NO_DISCOUNT_PRICE;
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
