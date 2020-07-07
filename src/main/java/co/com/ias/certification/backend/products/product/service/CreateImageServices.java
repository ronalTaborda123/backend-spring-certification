package co.com.ias.certification.backend.products.product.service;


import org.springframework.stereotype.Service;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.UploadImageRequest;
import co.com.ias.certification.backend.products.product.port.in.UploadImageUseCase;
import co.com.ias.certification.backend.products.product.port.out.UploadImagePort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateImageServices implements UploadImageUseCase {

    private  final UploadImagePort uploadImagePort;
    @Override
    public Try<String> createImage(UploadImageRequest uploadImageRequest) {
        return uploadImagePort.createImage(uploadImageRequest);
    }
}
