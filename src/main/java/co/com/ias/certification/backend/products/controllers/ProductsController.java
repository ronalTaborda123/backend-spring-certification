package co.com.ias.certification.backend.products.controllers;


import org.keycloak.KeycloakPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.vavr.control.Try;

import java.io.IOException;
import java.util.List;

import co.com.ias.certification.backend.products.product.domain.*;
import co.com.ias.certification.backend.products.product.port.in.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/apli/v1/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductsController {

    private final CreateProductUseCase createProductUseCase;
    private final ListProductUseCase listProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UploadImageUseCase uploadImageUseCase;
    private final ListImageUseCase listImageUseCase;
    private final SearchProductUseCase searchProductUseCase;

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

    @GetMapping("/{name}")
    public Try<List<Product>>getProductById(Authentication authentication, @PathVariable String name){
        Name nameRequest=Name.of(name);

        KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal)authentication.getPrincipal();

        return searchProductUseCase.getProduct(nameRequest);
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

    @PostMapping("/images")
    public Try<String> uploadImages(@RequestParam("file") MultipartFile file, @RequestParam("id") Long productId) throws IOException {
        ProductId id = ProductId.of(productId);
        UploadImageRequest uploadImageRequest= UploadImageRequest.of(id,file.getName(),file.getContentType(), file.getBytes());
        return uploadImageUseCase.createImage(uploadImageRequest);

    }
    @GetMapping("/images/{id}")
    public Try<List<Image>> getFile(@PathVariable Long productId) {
        ProductId id = ProductId.of(productId);
        return listImageUseCase.getFile(id);
    }
}
