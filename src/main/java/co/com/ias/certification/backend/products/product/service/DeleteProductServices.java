package co.com.ias.certification.backend.products.product.service;

import org.springframework.stereotype.Service;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.Product;
import co.com.ias.certification.backend.products.product.domain.ProductId;
import co.com.ias.certification.backend.products.product.port.in.DeleteProductUseCase;
import co.com.ias.certification.backend.products.product.port.out.DeleteProductPort;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeleteProductServices implements DeleteProductUseCase {

    private final DeleteProductPort deleteProductPort;

    @Override
    public Try<Product> deleteProduct(ProductId productId) {
        return deleteProductPort.deleteProduct(productId);
    }
}
