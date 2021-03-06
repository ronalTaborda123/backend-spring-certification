package co.com.ias.certification.backend.products.product.domain;


import org.apache.commons.lang3.StringUtils;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.StringSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class Name implements StringSerializable {

    String value;

    public Name(String value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(StringUtils.isNoneBlank(value));
        Preconditions.checkArgument(value.length() <= 100);
        this.value = value;
    }

    @Override
    public String valueOf() {
        return value;
    }
}
