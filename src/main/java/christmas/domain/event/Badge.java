package christmas.domain.event;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String name;
    private final int price;

    Badge(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    static public Badge of(int price) {
        if (price < STAR.price) {
            return NONE;
        }
        if (price < TREE.price) {
            return STAR;
        }
        if (price < SANTA.price) {
            return TREE;
        }
        return SANTA;
    }
}
