package co.com.ias.certification.backend.products.product.service;


import org.springframework.stereotype.Service;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.Product;
import co.com.ias.certification.backend.products.product.domain.ProductId;
import co.com.ias.certification.backend.products.product.domain.ProductOperationRequest;
import co.com.ias.certification.backend.products.product.port.in.UpdateProductUseCase;
import co.com.ias.certification.backend.products.product.port.out.UpdateProductPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateProductServices implements UpdateProductUseCase {
    private final UpdateProductPort updateProductPort;

    @Override
    public Try<Product> updateProduct(ProductId productId, ProductOperationRequest productOperationRequest) {
        return updateProductPort.updateProduct(productId,productOperationRequest);
    }
}
