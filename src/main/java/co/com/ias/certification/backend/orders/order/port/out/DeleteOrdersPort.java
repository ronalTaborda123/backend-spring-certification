package co.com.ias.certification.backend.orders.order.port.out;

import io.vavr.control.Try;

import co.com.ias.certification.backend.orders.order.domain.Order;
import co.com.ias.certification.backend.orders.order.domain.OrdersId;

public interface DeleteOrdersPort {
    Try<Order> deleteOrder(OrdersId ordersId);
}
