package christmas.dto;

import christmas.domain.order.Order;

public class OrderDTO {
    private final String menu;
    private final int count;

    public OrderDTO(String menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public String getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }

    public static OrderDTO of(Order order) {
        return new OrderDTO(order.getMenuName(), order.getCount());
    }

    public Order toOrder() {
        return new Order(menu, count);
    }
}
