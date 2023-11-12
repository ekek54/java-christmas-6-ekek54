package christmas.domain.event;

import christmas.domain.order.Menu;
import christmas.domain.order.Orders;
import christmas.vo.VisitDate;
import java.util.Map;

public interface Event {
    boolean isApplied(VisitDate visitDate, Orders orders);

    int discountPrice(VisitDate visitDate, Orders orders);
    int giftPrice(Orders orders);
    Map<Menu, Integer> giftMenus(Orders orders);
    String getName();
}
