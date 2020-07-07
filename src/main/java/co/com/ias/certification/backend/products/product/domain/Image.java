package co.com.ias.certification.backend.products.product.domain;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Image {
    Long idimages;
    ProductId id;
    String name;
    String mimetype;
    byte[] pic;

    public Image(Long idimages, ProductId id,String name,String mimetype,byte[] pic){
        this.idimages=idimages;
        this.id=id;
        this.name=name;
        this.mimetype=mimetype;
        this.pic=pic;
    }
}
