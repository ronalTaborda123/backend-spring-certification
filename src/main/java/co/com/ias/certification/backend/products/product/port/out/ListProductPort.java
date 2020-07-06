package co.com.ias.certification.backend.products.product.port.out;

import java.util.List;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.Product;

public interface ListProductPort {
    Try<List<Product>> getListProduct();
//    List<Product> getListProduct();
}
