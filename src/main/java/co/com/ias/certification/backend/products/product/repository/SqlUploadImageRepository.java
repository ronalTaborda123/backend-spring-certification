package co.com.ias.certification.backend.products.product.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.Mapper.ImagesMapper;
import co.com.ias.certification.backend.products.product.domain.Image;
import co.com.ias.certification.backend.products.product.domain.UploadImageRequest;
import co.com.ias.certification.backend.products.product.Mapper.ProductMapper;
import co.com.ias.certification.backend.products.product.domain.ProductId;
import co.com.ias.certification.backend.products.product.port.out.ListImagePort;
import co.com.ias.certification.backend.products.product.port.out.UploadImagePort;

public class SqlUploadImageRepository implements UploadImagePort, ListImagePort {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public SqlUploadImageRepository(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Override
    public Try<String> createImage(UploadImageRequest uploadImageRequest) {
        return Try.of(()->{
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("name", uploadImageRequest.getName());
            parameters.put("type", uploadImageRequest.getMimetype());
            parameters.put("picByte", uploadImageRequest.getPic());
            int[] number;


            number = simpleJdbcInsert.executeBatch(parameters);
            return null;
        });
    }

    @Override
    public Try<List<Image>> getFile(ProductId id) {
        return Try.of(()-> {
            String SQL = "SELECT ID, IDIMAGES, NAME, TYPE, PICBYTE  FROM IMAGES WHERE NAME=?";
            Object[] objects = {id.getValue()};
            List<Image> list=jdbcTemplate.query(SQL,objects, new ImagesMapper());
            return  list;
        });
    }
}
