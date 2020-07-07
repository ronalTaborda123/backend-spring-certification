package co.com.ias.certification.backend.products.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


import co.com.ias.certification.backend.orders.order.port.out.CreateOrderPort;
import co.com.ias.certification.backend.orders.order.repository.SqlOrderRepository;
import co.com.ias.certification.backend.products.product.port.out.CreateProductPort;
import co.com.ias.certification.backend.products.product.port.out.UploadImagePort;
import co.com.ias.certification.backend.products.product.repository.SqlProductRepository;
import co.com.ias.certification.backend.products.product.repository.SqlUploadImageRepository;

@Configuration
public class RepositoryConfiguration {

    @Bean
    @Profile({"dev", "prod"})
    public CreateProductPort createProductPort(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PRODUCTS")
                .usingGeneratedKeyColumns("ID");

        return new SqlProductRepository(jdbcTemplate, simpleJdbcInsert);
    }

    @Bean
    @Profile({"dev", "prod"})
    public CreateOrderPort createOrderPort(JdbcTemplate jdbcTemplate, DataSource dataSource){
        SimpleJdbcInsert simpleJdbcInsert=new SimpleJdbcInsert(dataSource)
                .withTableName("ORDERS")
                .usingGeneratedKeyColumns("IDORDERS");
        return new SqlOrderRepository(jdbcTemplate, simpleJdbcInsert);

    }

    @Bean
    @Profile({"dev", "prod"})
    public UploadImagePort UploadImagePort(JdbcTemplate jdbcTemplate, DataSource dataSource){
        SimpleJdbcInsert simpleJdbcInsert=new SimpleJdbcInsert(dataSource)
                .withTableName("IMAGES")
                .usingGeneratedKeyColumns("ID");
        return new SqlUploadImageRepository(jdbcTemplate, simpleJdbcInsert);

    }

}
