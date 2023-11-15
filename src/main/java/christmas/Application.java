package christmas;

import christmas.controller.Controller;
import christmas.controller.ControllerBuilder;
import christmas.domain.event.ChristmasEvent;
import christmas.domain.event.Event;
import christmas.domain.event.GiftEvent;
import christmas.domain.event.SpecialEvent;
import christmas.domain.event.WeekdayEvent;
import christmas.domain.event.WeekendEvent;
import christmas.domain.order.Orders;
import christmas.service.EventService;
import christmas.util.InputErrorHandler;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.vo.VisitDate;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        InputErrorHandler<VisitDate> visitDateInputErrorHandler = new InputErrorHandler<>("유효하지 않은 날짜입니다.");
        InputErrorHandler<Orders> ordersInputErrorHandler = new InputErrorHandler<>("유효하지 않은 주문입니다.");
        List<Event> events = initEvents();
        EventService eventService = new EventService(events);
        Controller controller = new ControllerBuilder().setInputView(inputView)
                .setOutputView(outputView)
                .setEventService(eventService)
                .setVisitDateHandler(visitDateInputErrorHandler)
                .setOrdersHandler(ordersInputErrorHandler)
                .createController();
        controller.run();
    }

    private static List<Event> initEvents() {
        return List.of(new WeekdayEvent(), new WeekendEvent(), new SpecialEvent(), new GiftEvent(), new ChristmasEvent());
    }
}
