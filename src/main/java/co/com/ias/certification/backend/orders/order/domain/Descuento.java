package co.com.ias.certification.backend.orders.order.domain;

import java.math.BigDecimal;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.NumberSerializable;
import lombok.Value;


@Value(staticConstructor = "of")
public class Descuento implements NumberSerializable {
    public static Descuento fromNumber(Number number) {
        return new Descuento(BigDecimal.valueOf(number.doubleValue()));
    }
    BigDecimal value;


    private Descuento(BigDecimal value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0);
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
