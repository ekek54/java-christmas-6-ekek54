package christmas.domain.order;

public class Order {
    private static final int MINIMUM_COUNT = 1;
    private static final String COUNT_INVALID_ERROR_MESSAGE = "주문 수량은 1개 이상이어야 합니다.";
    private final Menu menu;
    private final int count;

    public Order(String menu, int count) {
        validateCount(count);
        this.menu = Menu.of(menu);
        this.count = count;
    }

    private void validateCount(int count) {
        if (count < MINIMUM_COUNT) {
            throw new IllegalArgumentException(COUNT_INVALID_ERROR_MESSAGE);
        }
    }

    @Override
    public int hashCode() {
        return menu.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order o) {
            return o.equalsMenu(menu);
        }
        return false;
    }

    public boolean equalsMenu(Menu menu) {
        return this.menu.equals(menu);
    }

    public int sumCount(int sum) {
        return sum + count;
    }

    public boolean isDrink() {
        return menu.isDrink();
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getCount() {
        return count;
    }
}
