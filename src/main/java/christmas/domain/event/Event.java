package christmas.domain.event;

import christmas.domain.order.Menu;
import christmas.domain.order.Orders;
import christmas.vo.VisitDate;
import java.util.Map;

public interface Event {
    boolean isApplyAt(VisitDate visitDate);

    int discountPrice(Orders orders);
    int giftPrice(Orders orders);
    Map<Menu, Integer> giftMenus(Orders orders);

}
