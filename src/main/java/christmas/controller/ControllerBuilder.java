package christmas.controller;

import christmas.domain.order.Orders;
import christmas.service.EventService;
import christmas.util.InputErrorHandler;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.vo.VisitDate;

public class ControllerBuilder {
    private InputView inputView;
    private OutputView outputView;
    private EventService eventService;
    private InputErrorHandler<VisitDate> visitDateHandler;
    private InputErrorHandler<Orders> ordersHandler;

    public ControllerBuilder setInputView(InputView inputView) {
        this.inputView = inputView;
        return this;
    }

    public ControllerBuilder setOutputView(OutputView outputView) {
        this.outputView = outputView;
        return this;
    }

    public ControllerBuilder setEventService(EventService eventService) {
        this.eventService = eventService;
        return this;
    }

    public ControllerBuilder setVisitDateHandler(InputErrorHandler<VisitDate> visitDateHandler) {
        this.visitDateHandler = visitDateHandler;
        return this;
    }

    public ControllerBuilder setOrdersHandler(InputErrorHandler<Orders> ordersHandler) {
        this.ordersHandler = ordersHandler;
        return this;
    }

    public Controller createController() {
        return new Controller(inputView, outputView, eventService, visitDateHandler, ordersHandler);
    }
}