package co.com.ias.certification.backend.products.product.domain;


import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.NumberSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class InventoryQuantity implements NumberSerializable {
    public static InventoryQuantity fromNumber(Number number) {
        return new InventoryQuantity(number.intValue());
    }

    Integer value;

    public InventoryQuantity(Integer value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value >= 0);
        this.value = value;
    }

    @Override
    public Integer valueOf() {

        return value;
    }
}
