package co.com.ias.certification.backend.products.product.port.out;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.Product;
import co.com.ias.certification.backend.products.product.domain.ProductId;
import co.com.ias.certification.backend.products.product.domain.ProductOperationRequest;

public interface UpdateProductPort {
    Try<Product> updateProduct(ProductId productId, ProductOperationRequest productOperationRequest);
}
