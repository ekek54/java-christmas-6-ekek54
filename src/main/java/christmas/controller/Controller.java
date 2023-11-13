package christmas.controller;

import christmas.domain.order.Orders;
import christmas.dto.BadgeDTO;
import christmas.dto.EventLogDTO;
import christmas.dto.GiftDTO;
import christmas.dto.OrderDTO;
import christmas.service.EventService;
import christmas.util.InputErrorHandler;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.vo.VisitDate;
import java.util.List;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final EventService eventService;
    private final InputErrorHandler<VisitDate> visitDateHandler;
    private final InputErrorHandler<Orders> ordersHandler;

    public Controller(InputView inputView, OutputView outputView, EventService eventService,
                      InputErrorHandler<VisitDate> visitDateHandler, InputErrorHandler<Orders> ordersHandler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventService = eventService;
        this.visitDateHandler = visitDateHandler;
        this.ordersHandler = ordersHandler;
    }

    public void run() {
        outputView.printWelcomeMessage();
        VisitDate visitDate = visitDateHandler.printErrorAndRetry(this::bookVisitDate);
        Orders orders = ordersHandler.printErrorAndRetry(this::getOrder);
        eventPreview(visitDate, orders);
    }

    private VisitDate bookVisitDate() {
        int inputVisitDate = inputView.readVisitDate();
        return VisitDate.of(inputVisitDate);
    }

    private Orders getOrder() {
        List<OrderDTO> orderDTOS = inputView.readOrders();
        return OrderDTO.toOrders(orderDTOS);
    }

    private void eventPreview(VisitDate visitDate, Orders orders) {
        outputView.printEventPreviewMessage(visitDate.getDate());
        orderLog(orders);
        eventAppliedLog(visitDate, orders);
        payment(visitDate, orders);
        eventBadge(visitDate, orders);
    }

    private void orderLog(Orders orders) {
        List<OrderDTO> orderDTOS = OrderDTO.listOf(orders.getOrders());
        outputView.printOrders(orderDTOS);
        int totalPrice = orders.calculateTotalPrice();
        outputView.printTotalPrice(totalPrice);
    }

    private void eventAppliedLog(VisitDate visitDate, Orders orders) {
        List<GiftDTO> giftDTOS = GiftDTO.listOf(eventService.totalGiftMenus(visitDate, orders));
        outputView.printGifts(giftDTOS);
        List<EventLogDTO> eventLogDTOS = EventLogDTO.listOf(eventService.appliedEventLogs(visitDate, orders));
        outputView.printEventLogs(eventLogDTOS);
        int totalEventPrice = eventService.totalEventPrice(visitDate, orders);
        outputView.printTotalEventPrice(totalEventPrice);
    }

    private void payment(VisitDate visitDate, Orders orders) {
        int paymentPrice = eventService.calculatePaymentPrice(visitDate, orders);
        outputView.printPaymentAmount(paymentPrice);
    }

    private void eventBadge(VisitDate visitDate, Orders orders) {
        BadgeDTO badge = BadgeDTO.of(eventService.chooseBadge(visitDate, orders));
        outputView.printBadge(badge);
    }
}
