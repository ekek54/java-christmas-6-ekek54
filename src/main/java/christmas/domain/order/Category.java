package christmas.domain.order;

public enum Category {
    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private final String name;

    Category(String name) {
        this.name = name;
    }
}
