package christmas.dto;

import christmas.domain.event.Badge;

public class BadgeDTO {
    private final String name;

    public BadgeDTO(String name) {
        this.name = name;
    }

    public static BadgeDTO of(Badge badge) {
        return new BadgeDTO(badge.getName());
    }

    public String getName() {
        return name;
    }
}
