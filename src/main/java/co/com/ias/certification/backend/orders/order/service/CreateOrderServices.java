package co.com.ias.certification.backend.orders.order.service;

import org.springframework.stereotype.Service;

import io.vavr.control.Try;

import co.com.ias.certification.backend.orders.order.domain.Order;
import co.com.ias.certification.backend.orders.order.domain.OrderOperationRequest;
import co.com.ias.certification.backend.orders.order.port.in.CreateOrdersUseCase;
import co.com.ias.certification.backend.orders.order.port.out.CreateOrderPort;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CreateOrderServices implements CreateOrdersUseCase {

    private final CreateOrderPort createOrderPort;
    @Override
    public Try<Order> createOrder(OrderOperationRequest orderOperationRequest) {
        return createOrderPort.createOrder(orderOperationRequest);
    }
}
