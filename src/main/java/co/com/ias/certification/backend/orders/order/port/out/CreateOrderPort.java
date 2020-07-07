package co.com.ias.certification.backend.orders.order.port.out;

import io.vavr.control.Try;

import co.com.ias.certification.backend.orders.order.domain.Order;
import co.com.ias.certification.backend.orders.order.domain.OrderOperationRequest;


public interface CreateOrderPort {
    Try<Order> createOrder(OrderOperationRequest orderOperationRequest);
}
