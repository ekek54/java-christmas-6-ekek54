package christmas.view;

import christmas.dto.BadgeDTO;
import christmas.dto.EventLogDTO;
import christmas.dto.GiftDTO;
import christmas.dto.OrderDTO;
import java.util.List;

public class OutputView {
    private static final String ORDER_FORMAT = "%s %d개";
    private static final String GIFT_FORMAT = "%s %d개";
    private static final String TOTAL_PRICE_FORMAT = "총 금액은 %d원입니다.";
    private static final String DISCOUNT_PRICE_FORMAT = "-%,d원";
    private static final String PRICE_FORMAT = "%,d원";
    private static final String EVENT_LOG_FORMAT = "%s: " + DISCOUNT_PRICE_FORMAT;

    public void printOrders(List<OrderDTO> orders) {
        System.out.println("<주문 메뉴>");
        orders.forEach(this::printOrder);
        printEmptyLine();
    }

    private void printOrder(OrderDTO orderDTO) {
        System.out.println(String.format(ORDER_FORMAT, orderDTO.getMenu(), orderDTO.getCount()));
    }

    public void printTotalPrice(int totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format(TOTAL_PRICE_FORMAT, totalPrice));
        printEmptyLine();
    }

    public void printGifts(List<GiftDTO> gifts) {
        System.out.println("<증정 메뉴>");
        if (gifts.isEmpty()) {
            System.out.println("없음");
            printEmptyLine();
            return;
        }
        gifts.forEach(this::printGift);
        printEmptyLine();
    }

    private void printGift(GiftDTO giftDTO) {
        System.out.println(String.format(GIFT_FORMAT, giftDTO.getMenu(), giftDTO.getCount()));
    }

    public void printEventLogs(List<EventLogDTO> eventLogs) {
        System.out.println("<혜택 내역>");
        if (eventLogs.isEmpty()) {
            System.out.println("없음");
            printEmptyLine();
            return;
        }
        eventLogs.forEach(this::printEventLog);
        printEmptyLine();
    }

    private void printEventLog(EventLogDTO eventLogDTO) {
        System.out.println(String.format(EVENT_LOG_FORMAT, eventLogDTO.getName(), eventLogDTO.getDiscountPrice()));
    }

    public void printTotalEventPrice(int totalDiscountPrice) {
        System.out.println("<총혜택 금액>");
        System.out.println(String.format(DISCOUNT_PRICE_FORMAT, totalDiscountPrice));
        printEmptyLine();
    }

    public void printPaymentAmount(int paymentAmount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format(PRICE_FORMAT, paymentAmount));
        printEmptyLine();
    }

    public void printBadge(BadgeDTO badgeDTO) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badgeDTO.getName());
    }

    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventPreviewMessage(int date) {
        System.out.println(String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date));
        printEmptyLine();
    }

    private void printEmptyLine() {
        System.out.println();
    }
}
