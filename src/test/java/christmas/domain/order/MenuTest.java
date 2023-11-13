package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    @DisplayName("메뉴에 없는 메뉴를 조회하면 예외가 발생한다.")
    void ofWithNotExistMenu() {
        //given
        String menu = "메뉴";
        //when
        //then
        Assertions.assertThatThrownBy(() -> Menu.of(menu))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("존재하지 않는 메뉴입니다.");
    }

    @Test
    @DisplayName("메뉴에 있는 메뉴를 조회하면 메뉴가 반환된다.")
    void ofWithExistMenu() {
        //given
        String menu = "양송이수프";
        //when
        Menu soup = Menu.of(menu);
        //then
        assertThat(soup).isNotNull();
    }

    @Test
    @DisplayName("메뉴의 이름을 조회하면 메뉴의 이름이 반환된다.")
    void getName() {
        //given
        String menu = "양송이수프";
        //when
        Menu soup = Menu.of(menu);
        //then
        assertThat(soup.getName()).isEqualTo(menu);
    }

    @Test
    @DisplayName("메뉴 이름이 다르면 동등한 메뉴가 아니다.")
    void equals() {
        //given
        String menu = "양송이수프";
        //when
        Menu soup = Menu.of(menu);
        //then
        assertThat(soup.equals(Menu.of("티본스테이크"))).isFalse();
    }

    @Test
    @DisplayName("다른 인스턴스 객체와 비교시 동등하지 않다.")
    void equalsWithDifferentInstance() {
        //given
        String menu = "양송이수프";
        //when
        Menu soup = Menu.of(menu);
        //then
        assertThat(soup.equals(new Object())).isFalse();
    }

    @Test
    @DisplayName("메뉴가 n개일 때의 가격을 계산한다")
    void calculatePrice() {
        //given
        String menu = "양송이수프";
        //when
        Menu soup = Menu.of(menu);
        //then
        assertThat(soup.calculatePrice(3)).isEqualTo(18000);
    }
}