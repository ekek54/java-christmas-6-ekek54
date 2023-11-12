package christmas.dto;

import java.util.List;
import java.util.Map;

public class EventLogDTO {
    private final String name;
    private final int discountPrice;

    public EventLogDTO(String event, int discountPrice) {
        this.name = event;
        this.discountPrice = discountPrice;
    }

    public String getName() {
        return name;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public static List<EventLogDTO> listOf(Map<String, Integer> appliedEventLogs) {
        return appliedEventLogs.entrySet()
                .stream()
                .map(entry -> new EventLogDTO(entry.getKey(), entry.getValue()))
                .toList();
    }
}
