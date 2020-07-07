package co.com.ias.certification.backend.orders.order.service;

import org.springframework.stereotype.Service;

import io.vavr.control.Try;

import co.com.ias.certification.backend.orders.order.domain.Order;
import co.com.ias.certification.backend.orders.order.domain.OrdersId;
import co.com.ias.certification.backend.orders.order.port.in.DeleteOrdersUseCase;
import co.com.ias.certification.backend.orders.order.port.out.DeleteOrdersPort;
import co.com.ias.certification.backend.products.product.port.out.DeleteProductPort;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteOrderServices implements DeleteOrdersUseCase {
    private final DeleteOrdersPort deleteOrdersPort;

    @Override
    public Try<Order> deleteOrder(OrdersId ordersId) {
        return deleteOrdersPort.deleteOrder(ordersId);
    }
}
