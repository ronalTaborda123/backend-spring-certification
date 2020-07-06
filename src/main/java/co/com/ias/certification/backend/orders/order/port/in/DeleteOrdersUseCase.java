package co.com.ias.certification.backend.orders.order.port.in;

import io.vavr.control.Try;

import co.com.ias.certification.backend.orders.order.domain.Order;
import co.com.ias.certification.backend.orders.order.domain.OrdersId;

public interface DeleteOrdersUseCase {

    Try<Order> deleteOrder(OrdersId ordersId);
}
