package co.com.ias.certification.backend.products.product.domain;

import co.com.ias.certification.backend.orders.order.domain.OrdersId;
import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Product {
    ProductId id;
    Name name;
    Description description;
    BasePrice basePrice;
    TaxRate taxRate;
    ProductStatus productStatus;
    InventoryQuantity inventoryQuantity;

    public Product(ProductId id, Name name, Description description, BasePrice basePrice, TaxRate taxRate, ProductStatus productStatus, InventoryQuantity inventoryQuantity) {
        this.id = Preconditions.checkNotNull(id);
        this.name = Preconditions.checkNotNull(name);
        this.description = Preconditions.checkNotNull(description);
        this.basePrice = Preconditions.checkNotNull(basePrice);
        this.taxRate = Preconditions.checkNotNull(taxRate);
        this.productStatus = Preconditions.checkNotNull(productStatus);
        this.inventoryQuantity = Preconditions.checkNotNull(inventoryQuantity);
    }
}
