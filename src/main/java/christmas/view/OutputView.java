package christmas.view;

import christmas.dto.EventLogDTO;
import christmas.dto.OrderDTO;
import christmas.dto.GiftDTO;
import java.text.DecimalFormat;
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
    }

    private void printOrder(OrderDTO orderDTO) {
        System.out.println(String.format(ORDER_FORMAT, orderDTO.getMenu(), orderDTO.getCount()));
    }

    public void printTotalPrice(int totalPrice) {
        System.out.println(String.format(TOTAL_PRICE_FORMAT, totalPrice));
    }

    public void printGifts(List<GiftDTO> gifts) {
        System.out.println("<증정 메뉴>");
        gifts.forEach(this::printGift);
    }

    private void printGift(GiftDTO giftDTO) {
        System.out.println(String.format(GIFT_FORMAT, giftDTO.getMenu(), giftDTO.getCount()));
    }

    public void printEventLogs(List<EventLogDTO> eventLogs) {
        System.out.println("<혜택 내역>");
        eventLogs.forEach(this::printEventLog);
    }

    private void printEventLog(EventLogDTO eventLogDTO) {
        System.out.println(String.format(EVENT_LOG_FORMAT, eventLogDTO.getName(), eventLogDTO.getDiscountPrice()));
    }

    public void printTotalDiscountPrice(int totalDiscountPrice) {
        System.out.println("<총 할인 금액>");
        System.out.println(String.format(DISCOUNT_PRICE_FORMAT, totalDiscountPrice));
    }

    public void printPaymentAmount(int paymentAmount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format(PRICE_FORMAT, paymentAmount));
    }
}
