package co.com.ias.certification.backend.products.product.port.out;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.Product;
import co.com.ias.certification.backend.products.product.domain.ProductId;

public interface DeleteProductPort {
    Try<Product> deleteProduct(ProductId productId);
}
