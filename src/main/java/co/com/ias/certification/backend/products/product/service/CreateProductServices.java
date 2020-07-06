package co.com.ias.certification.backend.products.product.service;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.vavr.control.Try;
import co.com.ias.certification.backend.products.product.domain.Product;
import co.com.ias.certification.backend.products.product.domain.ProductOperationRequest;
import co.com.ias.certification.backend.products.product.port.in.CreateProductUseCase;
import co.com.ias.certification.backend.products.product.port.out.CreateProductPort;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CreateProductServices implements  CreateProductUseCase{

    private final CreateProductPort createProductPort;

    @Override
    public Try<Product> createProduct(ProductOperationRequest productOperationRequest) {
        return createProductPort.createProduct(productOperationRequest);
    }
}
