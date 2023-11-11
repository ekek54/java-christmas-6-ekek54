package christmas.dto;

public class OrderRequestDTO {
    private final String menu;
    private final int count;

    public OrderRequestDTO(String menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public String getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }
}
