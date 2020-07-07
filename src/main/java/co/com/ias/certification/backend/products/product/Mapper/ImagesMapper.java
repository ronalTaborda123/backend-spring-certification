package co.com.ias.certification.backend.products.product.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.ias.certification.backend.products.product.domain.Image;
import co.com.ias.certification.backend.products.product.domain.ProductId;

public class ImagesMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet resultSet, int i) throws SQLException {
        return Image.builder().
                id(ProductId.of(resultSet.getLong("ID"))).
                idimages(resultSet.getLong("ID")).
                name(resultSet.getString("NAME")).
                mimetype(resultSet.getString("TYPE")).pic(resultSet.getBytes("PICBYTE")).build();
    }
}
