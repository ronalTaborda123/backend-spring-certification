package co.com.ias.certification.backend.products.product.port.in;


import java.util.List;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.Product;

public interface ListProductUseCase {
   Try<List<Product>>getListProduct();
//    List<Product>getListProduct();
}
