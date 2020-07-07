package co.com.ias.certification.backend.products.product.port.in;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.UploadImageRequest;

public interface UploadImageUseCase {
    Try<String> createImage(UploadImageRequest uploadImageRequest);
}
