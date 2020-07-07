package co.com.ias.certification.backend.products.product.domain;

import java.io.InputStream;

import co.com.ias.certification.backend.products.product.domain.ProductId;
import lombok.Value;

@Value(staticConstructor = "of")
public class UploadImageRequest {
    ProductId id;
    String name;
    String mimetype;
    byte[] pic;
}
