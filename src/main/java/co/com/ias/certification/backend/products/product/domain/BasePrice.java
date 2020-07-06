package co.com.ias.certification.backend.products.product.domain;

import java.math.BigDecimal;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.NumberSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class BasePrice implements NumberSerializable {
    
    public static BasePrice fromNumber(Number number) {
        return new BasePrice(BigDecimal.valueOf(number.doubleValue()));
    }

    BigDecimal value;

    private BasePrice(BigDecimal value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0);
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
