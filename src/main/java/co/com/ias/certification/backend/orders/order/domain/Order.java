package co.com.ias.certification.backend.orders.order.domain;


import java.util.List;

import co.com.ias.certification.backend.products.product.domain.Product;
import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Order {

    OrdersId ordersId;
    Client client;
    Total total;
    Descuento descuento;
    OrderStatus orderStatus;
    List<Product> productList;

    public Order(OrdersId ordersId,Client client,Total total,Descuento descuento,OrderStatus orderStatus,List<Product> productList){
        this.ordersId= Preconditions.checkNotNull(ordersId);
        this.client=Preconditions.checkNotNull(client);
        this.total=Preconditions.checkNotNull(total);
        this.descuento=Preconditions.checkNotNull(descuento);
        this.orderStatus=Preconditions.checkNotNull(orderStatus);
        this.productList=Preconditions.checkNotNull(productList);
    }
}
