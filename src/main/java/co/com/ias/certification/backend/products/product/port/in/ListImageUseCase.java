package co.com.ias.certification.backend.products.product.port.in;

import java.util.List;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.Image;
import co.com.ias.certification.backend.products.product.domain.ProductId;

public interface ListImageUseCase {
    Try<List<Image>> getFile(ProductId id);
}
