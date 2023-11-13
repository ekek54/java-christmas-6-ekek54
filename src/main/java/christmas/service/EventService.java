package christmas.service;

import christmas.domain.event.Badge;
import christmas.domain.event.Event;
import christmas.domain.order.Menu;
import christmas.domain.order.Orders;
import christmas.vo.VisitDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventService {
    private static final int EVENT_APPLY_PRICE = 10000;
    private final List<Event> events;

    public EventService(List<Event> events) {
        this.events = events;
    }

    public int totalEventPrice(VisitDate visitDate, Orders orders) {
        return totalDiscountPrice(visitDate, orders) + totalGiftPrice(visitDate, orders);
    }

    public int totalDiscountPrice(VisitDate visitDate, Orders orders) {
        if (lessThanApplyPrice(orders.calculateTotalPrice())) {
            return 0;
        }
        return appliedEvents(visitDate, orders).stream()
                .mapToInt(event -> event.discountPrice(visitDate, orders))
                .sum();
    }

    public int totalGiftPrice(VisitDate visitDate, Orders orders) {
        if (lessThanApplyPrice(orders.calculateTotalPrice())) {
            return 0;
        }
        return appliedEvents(visitDate, orders).stream()
                .mapToInt(event -> event.giftPrice(orders))
                .sum();
    }

    private boolean lessThanApplyPrice(int totalPrice) {
        return totalPrice < EVENT_APPLY_PRICE;
    }

    private List<Event> appliedEvents(VisitDate visitDate, Orders orders) {
        return events.stream()
                .filter(event -> event.isApplied(visitDate, orders))
                .toList();
    }

    public Map<Menu, Integer> totalGiftMenus(VisitDate visitDate, Orders orders) {
        if (lessThanApplyPrice(orders.calculateTotalPrice())) {
            return new HashMap<>();
        }
        List<Map<Menu, Integer>> giftMenusGroup = appliedEvents(visitDate, orders).stream()
                .map(event -> event.giftMenus(orders)).toList();
        return mergeGiftMenusGroup(giftMenusGroup);
    }

    private Map<Menu, Integer> mergeGiftMenusGroup(List<Map<Menu, Integer>> giftMenusGroup) {
        HashMap<Menu, Integer> result = new HashMap<>();
        giftMenusGroup.forEach(giftMenus -> mergeGiftMenus(result, giftMenus));
        return result;
    }

    private void mergeGiftMenus(HashMap<Menu, Integer> result, Map<Menu, Integer> giftMenu) {
        giftMenu.forEach((menu, count) -> result.merge(menu, count, Integer::sum));
    }

    public Map<String, Integer> appliedEventLogs(VisitDate visitDate, Orders orders) {
        return appliedEvents(visitDate, orders).stream()
                .collect(
                        HashMap::new,
                        (map, event) -> map.put(event.getName(), event.eventPrice(visitDate, orders)),
                        HashMap::putAll
                );
    }

    public Badge chooseBadge(VisitDate visitDate, Orders orders) {
        return Badge.of(this.totalEventPrice(visitDate, orders));
    }
}
