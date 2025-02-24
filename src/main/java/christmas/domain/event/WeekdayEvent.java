package christmas.domain.event;

import christmas.domain.order.Category;
import christmas.domain.order.Menu;
import christmas.domain.order.Orders;
import christmas.vo.VisitDate;
import java.util.Map;

public class WeekdayEvent implements Event{
    private static final int DISCOUNT_PRICE_PER_DESSERT = 2023;
    private static final int NO_GIFT_PRICE = 0;
    private static final String name = "평일 할인";
    @Override
    public boolean isApplied(VisitDate visitDate, Orders orders) {
        return visitDate.isWeekday() && dessertExist(orders);
    }

    @Override
    public int discountPrice(VisitDate visitDate, Orders orders) {
        return orders.sumOfCountFilterBy(Category.DESSERT) * DISCOUNT_PRICE_PER_DESSERT;
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
        return WeekdayEvent.name;
    }

    private boolean dessertExist(Orders orders) {
        return orders.sumOfCountFilterBy(Category.DESSERT) > 0;
    }
}
