package christmas.service;

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

    public int totalEventPrice(Orders orders, VisitDate visitDate) {
        return totalDiscountPrice(orders, visitDate) + totalGiftPrice(orders, visitDate);
    }

    public int totalDiscountPrice(Orders orders, VisitDate visitDate) {
        if (!isEventApply(orders.calculateTotalPrice())) {
            return 0;
        }
        return appliedEvents(visitDate).stream()
                .mapToInt(event -> event.discountPrice(orders))
                .sum();
    }

    public int totalGiftPrice(Orders orders, VisitDate visitDate) {
        if (!isEventApply(orders.calculateTotalPrice())) {
            return 0;
        }
        return appliedEvents(visitDate).stream()
                .mapToInt(event -> event.giftPrice(orders))
                .sum();
    }

    private boolean isEventApply(int totalPrice) {
        return totalPrice >= EVENT_APPLY_PRICE;
    }

    private List<Event> appliedEvents(VisitDate visitDate) {
        return events.stream()
                .filter(event -> event.isApplyAt(visitDate))
                .toList();
    }

    public Map<Menu, Integer> totalGiftMenus(Orders orders, VisitDate visitDate) {
        if (!isEventApply(orders.calculateTotalPrice())) {
            return new HashMap<>();
        }
        List<Map<Menu, Integer>> giftMenusGroup = appliedEvents(visitDate).stream()
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
}
