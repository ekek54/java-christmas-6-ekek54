package christmas.view;

import christmas.dto.OrderDTO;
import java.util.List;

public class OutputView {
    private static final String ORDER_FORMAT = "%s %d개";
    private static final String TOTAL_PRICE_FORMAT = "총 금액은 %d원입니다.";

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


}
