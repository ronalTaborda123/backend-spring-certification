package co.com.ias.certification.backend.products.product.domain;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.NumberSerializable;
import lombok.Value;


@Value(staticConstructor = "of")
public class ProductId implements NumberSerializable {

    public static ProductId fromNumber(Number number) {
        return new ProductId(number.longValue());
    }
    Long value;

    private ProductId(Long value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value >= 1);
        this.value = value;
    }

    @Override
    public Number valueOf() {

        return value;
    }
}
