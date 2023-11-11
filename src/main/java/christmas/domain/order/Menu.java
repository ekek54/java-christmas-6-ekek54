package christmas.domain.order;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private static final String MENU_NOT_EXIST_ERROR_MESSAGE = "존재하지 않는 메뉴입니다.";
    private static final Map<String, Menu> menus= new HashMap<>();
    private final String name;
    private final int price;
    private final Category category;

    static {
        menus.put("양송이수프", new Menu("양송이수프", 6_000, Category.APPETIZER));
        menus.put("타파스", new Menu("타파스", 5_000, Category.APPETIZER));
        menus.put("시저샐러드", new Menu("시저샐러드", 8_000, Category.APPETIZER));
        menus.put("티본스테이크", new Menu("티본스테이크", 55_000, Category.MAIN));
        menus.put("바비큐립", new Menu("바비큐립", 54_000, Category.MAIN));
        menus.put("해산물파스타", new Menu("해산물파스타", 35_000, Category.MAIN));
        menus.put("크리스마스파스타", new Menu("크리스마스파스타", 25_000, Category.MAIN));
        menus.put("초코케이크", new Menu("초코케이크", 15_000, Category.DESSERT));
        menus.put("아이스크림", new Menu("아이스크림", 5_000, Category.DESSERT));
        menus.put("제로콜라", new Menu("제로콜라", 3_000, Category.DRINK));
        menus.put("레드와인", new Menu("레드와인", 60_000, Category.DRINK));
        menus.put("샴페인", new Menu("샴페인", 25_000, Category.DRINK));
    }

    private Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Menu of(String name) {
        validateMenuName(name);
        return menus.get(name);
    }

    private static void validateMenuName(String name) {
        if (!menus.containsKey(name)) {
            throw new IllegalArgumentException(MENU_NOT_EXIST_ERROR_MESSAGE);
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Menu m) {
            return m.equalsName(name);
        }
        return false;
    }

    public boolean equalsName(String name) {
        return this.name.equals(name);
    }

    public boolean isDrink() {
        return category == Category.DRINK;
    }

    public String getName() {
        return name;
    }
}
