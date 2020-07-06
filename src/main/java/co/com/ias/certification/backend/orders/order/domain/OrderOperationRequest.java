package co.com.ias.certification.backend.orders.order.domain;

import java.util.List;

import co.com.ias.certification.backend.products.product.domain.Product;
import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderOperationRequest {

    Client client;
    Total total;
    Descuento descuento;
    OrderStatus orderStatus;
    List<Product> productList;

    public OrderOperationRequest(Client client,Total total,Descuento descuento,OrderStatus orderStatus,List<Product> productList){
        this.client=Preconditions.checkNotNull(client);
        this.total=Preconditions.checkNotNull(total);
        this.descuento=Preconditions.checkNotNull(descuento);
        this.orderStatus=Preconditions.checkNotNull(orderStatus);
        this.productList=Preconditions.checkNotNull(productList);
    }
}
