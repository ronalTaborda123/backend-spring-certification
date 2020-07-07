package co.com.ias.certification.backend.products.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.Image;
import co.com.ias.certification.backend.products.product.domain.UploadImageRequest;
import co.com.ias.certification.backend.products.product.domain.ProductId;
import co.com.ias.certification.backend.products.product.port.in.ListImageUseCase;
import co.com.ias.certification.backend.products.product.port.out.ListImagePort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListImageServices  implements ListImageUseCase {

    private final ListImagePort listImagePort;

    @Override
    public Try<List<Image>> getFile(ProductId id) {
        return listImagePort.getFile(id);
    }
}
