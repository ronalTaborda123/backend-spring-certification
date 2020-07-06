package co.com.ias.certification.backend.products.product.port.out;

import org.springframework.stereotype.Repository;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.Product;
import co.com.ias.certification.backend.products.product.domain.ProductOperationRequest;

@Repository
public interface CreateProductPort {
    Try<Product> createProduct(ProductOperationRequest productOperationRequest);
}
