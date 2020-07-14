package co.com.ias.certification.backend.products.product.service;


import java.util.List;

import org.springframework.stereotype.Service;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.Name;
import co.com.ias.certification.backend.products.product.domain.Product;
import co.com.ias.certification.backend.products.product.port.in.SearchProductUseCase;
import co.com.ias.certification.backend.products.product.port.out.SearchProductPot;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchProductServices implements SearchProductUseCase {

    private final SearchProductPot searchProductPot;

    @Override
    public Try<List<Product>> getProduct(Name name) {
        return searchProductPot.getProduct(name);
    }
}
