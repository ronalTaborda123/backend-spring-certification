package co.com.ias.certification.backend.orders.order.domain;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.NumberSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class OrdersId implements NumberSerializable {
    public static OrdersId fromNumber(Number number) {
        return new OrdersId(number.longValue());
    }
    Long value;

    private OrdersId(Long value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value >= 1);
        this.value = value;
    }

    @Override
    public Number valueOf() {
        return value;
    }
}