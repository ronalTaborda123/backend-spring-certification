package co.com.ias.certification.backend.products.product.port.out;

import java.util.List;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.Image;
import co.com.ias.certification.backend.products.product.domain.ProductId;

public interface ListImagePort {
     Try<List<Image>> getFile(ProductId id);
}
