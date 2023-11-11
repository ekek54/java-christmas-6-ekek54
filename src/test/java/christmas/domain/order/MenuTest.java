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

}