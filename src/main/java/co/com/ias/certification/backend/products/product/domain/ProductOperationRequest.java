package co.com.ias.certification.backend.products.product.domain;

import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductOperationRequest {
    Name name;
    Description description;
    BasePrice basePrice;
    TaxRate taxRate;
    ProductStatus productStatus;
    InventoryQuantity inventoryQuantity;
}
