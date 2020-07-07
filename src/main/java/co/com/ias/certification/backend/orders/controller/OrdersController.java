package co.com.ias.certification.backend.orders.controller;

import org.keycloak.KeycloakPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import io.vavr.control.Try;

import co.com.ias.certification.backend.orders.order.domain.Order;
import co.com.ias.certification.backend.orders.order.domain.OrderOperationRequest;
import co.com.ias.certification.backend.orders.order.domain.OrdersId;
import co.com.ias.certification.backend.orders.order.port.in.CreateOrdersUseCase;
import co.com.ias.certification.backend.orders.order.port.in.DeleteOrdersUseCase;
import co.com.ias.certification.backend.products.product.domain.Product;
import co.com.ias.certification.backend.products.product.domain.ProductId;
import co.com.ias.certification.backend.products.product.port.in.DeleteProductUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/apli/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final CreateOrdersUseCase createOrdersUseCase;
//    private final DeleteOrdersUseCase deleteOrdersUseCase;

    @PostMapping
    public Try<Order> createProduct(Authentication authentication, @RequestBody OrderOperationRequest orderOperationRequest){
        KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal)authentication.getPrincipal();
        return createOrdersUseCase.createOrder(orderOperationRequest);
    }

//    @DeleteMapping("/{id}")
//    public Try<Order> deleteOrder(@PathVariable Long id){
//        OrdersId ordersId= OrdersId.of(id);
//        return deleteOrdersUseCase.deleteOrder(ordersId);
//    }

}
