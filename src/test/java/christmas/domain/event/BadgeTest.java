package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @Test
    @DisplayName("뱃지의 이름을 반환한다.")
    void getName() {
        //given
        Badge badge = Badge.of(5000);
        //when
        String badgeName = badge.getName();
        //then
        assertThat(badgeName).isEqualTo("별");
    }

    @Test
    @DisplayName("5000원 미만이면 뱃지를 발급하지 않는다.")
    void of() {
        //given
        int price = 4999;
        //when
        Badge badge = Badge.of(price);
        //then
        assertThat(badge).isEqualTo(Badge.NONE);
    }

    @Test
    @DisplayName("5000원 이상 10000원 미만이면 별 뱃지를 발급한다.")
    void ofWithStar() {
        //given
        int price = 5000;
        //when
        Badge badge = Badge.of(price);
        //then
        assertThat(badge).isEqualTo(Badge.STAR);
    }

    @Test
    @DisplayName("10000원 이상 20000원 미만이면 트리 뱃지를 발급한다.")
    void ofWithTree() {
        //given
        int price = 10000;
        //when
        Badge badge = Badge.of(price);
        //then
        assertThat(badge).isEqualTo(Badge.TREE);
    }

    @Test
    @DisplayName("20000원 이상이면 산타 뱃지를 발급한다.")
    void ofWithSanta() {
        //given
        int price = 20000;
        //when
        Badge badge = Badge.of(price);
        //then
        assertThat(badge).isEqualTo(Badge.SANTA);
    }
}