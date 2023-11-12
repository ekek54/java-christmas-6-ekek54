package christmas.dto;

import christmas.domain.order.Menu;
import java.util.List;
import java.util.Map;

public class GiftDTO {
    private final String menu;
    private final int count;

    public GiftDTO(String menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public String getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }

    public static List<GiftDTO> listOf(Map<Menu, Integer> giftMenus) {
        return giftMenus.entrySet()
                .stream()
                .map(entry -> new GiftDTO(entry.getKey().getName(), entry.getValue()))
                .toList();
    }
}
