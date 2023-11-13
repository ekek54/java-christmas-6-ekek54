package christmas.dto;

import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.util.List;

public class OrderDTO {
    private final String menu;
    private final int count;

    public OrderDTO(String menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public static OrderDTO of(Order order) {
        return new OrderDTO(order.getMenuName(), order.getCount());
    }

    public static Orders toOrders(List<OrderDTO> orderDTOs) {
        return new Orders(
                orderDTOs.stream()
                .map(OrderDTO::toOrder)
                .toList()
        );
    }

    public String getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }

    public Order toOrder() {
        return new Order(menu, count);
    }

    static public List<OrderDTO> listOf(List<Order> orders) {
        return orders.stream()
                .map(OrderDTO::of)
                .toList();
    }
}
