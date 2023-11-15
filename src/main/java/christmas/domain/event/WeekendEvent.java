package christmas.domain.event;

import christmas.domain.order.Category;
import christmas.domain.order.Menu;
import christmas.domain.order.Orders;
import christmas.vo.VisitDate;
import java.util.Map;

public class WeekendEvent implements Event{
    private static final int DISCOUNT_PRICE_PER_MAIN = 2023;
    private static final int NO_GIFT_PRICE = 0;
    private static final String name = "주말 할인";
    @Override
    public boolean isApplied(VisitDate visitDate, Orders orders) {
        return visitDate.isWeekend() && mainExist(orders);
    }

    @Override
    public int discountPrice(VisitDate visitDate, Orders orders) {
        return orders.sumOfCountFilterBy(Category.MAIN) * DISCOUNT_PRICE_PER_MAIN;
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
        return WeekendEvent.name;
    }

    private boolean mainExist(Orders orders) {
        return orders.sumOfCountFilterBy(Category.MAIN) > 0;
    }
}
