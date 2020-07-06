package co.com.ias.certification.backend.products.product.port.in;





import io.vavr.control.Try;
import co.com.ias.certification.backend.products.product.domain.Product;
import co.com.ias.certification.backend.products.product.domain.ProductOperationRequest;


public interface CreateProductUseCase {
    Try<Product> createProduct(ProductOperationRequest productOperationRequest);
}
