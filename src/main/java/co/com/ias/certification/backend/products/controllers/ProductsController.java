package co.com.ias.certification.backend.products.controllers;


import org.keycloak.KeycloakPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import io.vavr.control.Try;
import java.util.List;
import co.com.ias.certification.backend.products.product.domain.Product;
import co.com.ias.certification.backend.products.product.domain.ProductId;
import co.com.ias.certification.backend.products.product.domain.ProductOperationRequest;
import co.com.ias.certification.backend.products.product.port.in.CreateProductUseCase;
import co.com.ias.certification.backend.products.product.port.in.DeleteProductUseCase;
import co.com.ias.certification.backend.products.product.port.in.ListProductUseCase;
import co.com.ias.certification.backend.products.product.port.in.UpdateProductUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/apli/v1/products")
@RequiredArgsConstructor
public class ProductsController {

    private final CreateProductUseCase createProductUseCase;
    private final ListProductUseCase listProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    @PostMapping
    public Try<Product> createProduct( Authentication authentication, @RequestBody ProductOperationRequest productOperationRequest){
        KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal)authentication.getPrincipal();
        return createProductUseCase.createProduct(productOperationRequest);
    }

    @GetMapping
    public Try<List<Product>> getListProduct(Authentication authentication)     {
        KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal)authentication.getPrincipal();
        return listProductUseCase.getListProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product>getProductById(Authentication authentication, @PathVariable String id){
        System.out.println( id);
        KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal)authentication.getPrincipal();
        return null;
    }

    @PutMapping("/{id}")
    public Try<Product>updateProduct(@PathVariable Long id,@RequestBody ProductOperationRequest productOperationRequest) {
        ProductId productId=ProductId.of(id);
        return updateProductUseCase.updateProduct(productId,productOperationRequest);
    }

    @DeleteMapping("/{id}")
    public Try<Product> deleteProduct(@PathVariable Long id){
        ProductId productId=ProductId.of(id);
        return deleteProductUseCase.deleteProduct(productId);
    }
}
