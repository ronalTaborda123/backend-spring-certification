package co.com.ias.certification.backend.products.product.service;


import java.util.List;

import org.springframework.stereotype.Service;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.Product;
import co.com.ias.certification.backend.products.product.port.in.ListProductUseCase;
import co.com.ias.certification.backend.products.product.port.out.ListProductPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListProductServices implements ListProductUseCase {

    private final ListProductPort listProductPort;

    @Override
    public Try<List<Product>> getListProduct() {
        return listProductPort.getListProduct();
    }
}
