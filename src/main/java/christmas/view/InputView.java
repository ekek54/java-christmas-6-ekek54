package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String DATE_REGEXP = "^[1-31]$";
    private static final String DATE_PATTERN_ERROR_MESSAGE = "유효하지 않은 날짜입니다.";

    public int readBookDate() {
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
}
