package christmas.view;

import christmas.dto.OrderDTO;
import java.util.List;

public class OutputView {
    private static final String ORDER_FORMAT = "%s %d개";

    public void printOrders(List<OrderDTO> orders) {
        System.out.println("<주문 메뉴>");
        orders.forEach(this::printOrder);
    }

    private void printOrder(OrderDTO orderDTO) {
        System.out.println(String.format(ORDER_FORMAT, orderDTO.getMenu(), orderDTO.getCount()));
    }
}
