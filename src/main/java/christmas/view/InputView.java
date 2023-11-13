package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderDTO;
import java.util.List;

public class InputView {
    private static final String DATE_REGEXP = "^[0-9]+$";
    private static final String DATE_PATTERN_ERROR_MESSAGE = "유효하지 않은 날짜입니다.";
    private static final String ORDERS_REGEXP = "^[가-힣]+-[0-9]+(,[가-힣]+-[0-9]+)*$";
    private static final String ORDERS_PATTERN_ERROR_MESSAGE = "메뉴 형식이 올바르지 않습니다.";
    private static final String ORDERS_DELIMITER = ",";
    private static final String ORDER_DELIMITER = "-";

    public int readVisitDate() {
        System.out.println("12 월 중 식장 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return readDate();
    }

    private int readDate() {
        String input = Console.readLine();
        if (input.matches(DATE_REGEXP)) {
            return Integer.parseInt(input);
        }
        throw new IllegalArgumentException(DATE_PATTERN_ERROR_MESSAGE);
    }

    public List<OrderDTO> readOrders() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String ordersString = Console.readLine();
        validateOrders(ordersString);
        List<String> orderStrings = parseOrderString(ordersString);
        return orderStrings.stream().map(this::parseOrder).toList();
    }

    private void validateOrders(String input) {
        if (!input.matches(ORDERS_REGEXP)) {
            throw new IllegalArgumentException(ORDERS_PATTERN_ERROR_MESSAGE);
        }
    }

    private List<String> parseOrderString(String input) {
        return List.of(input.split(ORDERS_DELIMITER));
    }

    private OrderDTO parseOrder(String input) {
        String[] order = input.split(ORDER_DELIMITER);
        return new OrderDTO(order[0], Integer.parseInt(order[1]));
    }
}
