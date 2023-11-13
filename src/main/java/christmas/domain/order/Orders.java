package christmas.domain.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Orders {
    private final int MAX_ORDER_COUNT = 20;
    private final String MAX_ORDER_ERROR_MESSAGE = "주문 수량은 " + MAX_ORDER_COUNT + "개 이하여야 합니다.";
    private final String NOT_ONLY_DRINK_ERROR_MESSAGE = "음료만 주문할 수 없습니다.";
    private final Set<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = new HashSet<>(orders);
        validateOrderDuplicate(orders);
        validateMaxOrderCount();
        validateNotOnlyDrink();
    }

    private void validateOrderDuplicate(List<Order> orders) {
        if (this.orders.size() != orders.size()) {
            throw new IllegalArgumentException("중복된 주문이 존재합니다.");
        }
    }

    private void validateMaxOrderCount() {
        if (calculateTotalOrderCount() > MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(MAX_ORDER_ERROR_MESSAGE);
        }
    }

    private int calculateTotalOrderCount() {
        return orders.stream()
                .reduce(0, (sum, order) -> order.sumCount(sum), Integer::sum);
    }

    private void validateNotOnlyDrink() {
        if (orders.stream().allMatch(order -> order.isSameCategory(Category.DRINK))) {
            throw new IllegalArgumentException(NOT_ONLY_DRINK_ERROR_MESSAGE);
        }
    }

    public int calculateTotalPrice() {
        return orders.stream()
                .reduce(0, (sum, order) -> order.sumPrice(sum), Integer::sum);
    }

    public int sumOfCountFilterBy(Category category) {
        return orders.stream()
                .filter(order -> order.isSameCategory(category))
                .reduce(0, (sum, order) -> order.sumCount(sum), Integer::sum);
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(new ArrayList<>(orders));
    }
}
