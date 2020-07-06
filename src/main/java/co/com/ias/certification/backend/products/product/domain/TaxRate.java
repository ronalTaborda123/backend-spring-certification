package co.com.ias.certification.backend.products.product.domain;

import java.math.BigDecimal;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.NumberSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class TaxRate implements NumberSerializable {

    public static TaxRate fromNumber(Number number) {
        return new TaxRate(BigDecimal.valueOf(number.doubleValue()));
    }

    BigDecimal value;

    private TaxRate(BigDecimal value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0);
        Preconditions.checkArgument(value.compareTo(BigDecimal.ONE) <= 0);
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
