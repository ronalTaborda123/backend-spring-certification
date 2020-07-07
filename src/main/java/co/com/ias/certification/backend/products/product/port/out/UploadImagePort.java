package co.com.ias.certification.backend.products.product.port.out;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.UploadImageRequest;

public interface UploadImagePort {
    Try<String> createImage(UploadImageRequest uploadImageRequest);
}
