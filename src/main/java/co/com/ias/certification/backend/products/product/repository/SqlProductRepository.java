package co.com.ias.certification.backend.products.product.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.Mapper.ProductMapper;
import co.com.ias.certification.backend.products.product.domain.*;
import co.com.ias.certification.backend.products.product.port.out.*;


public class SqlProductRepository implements CreateProductPort, ListProductPort , UpdateProductPort , DeleteProductPort,SearchProductPot {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;


    public SqlProductRepository(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Override
    public Try<Product> createProduct(ProductOperationRequest productOperationRequest) {
        return Try.of(() -> {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", productOperationRequest.getName().getValue());
        parameters.put("description", productOperationRequest.getDescription().getValue());
        parameters.put("base_price", productOperationRequest.getBasePrice().getValue());
        parameters.put("tax_rate", productOperationRequest.getTaxRate().getValue());
        parameters.put("status", productOperationRequest.getProductStatus().toString());
        parameters.put("inventory_quantity",productOperationRequest.getInventoryQuantity().getValue());
        Number number=0;

        number = simpleJdbcInsert.execute(parameters);
        String SQL = "SELECT * FROM PRODUCTS ORDER BY ID DESC LIMIT 1";
        return jdbcTemplate.queryForObject(SQL,new ProductMapper());
        });
//        /return Product.of(productCreate(ProductId.of(product.getProductId().getValue()),productOperationRequest));
    }


    @Override
    public Try<List<Product>>getListProduct() {

        return Try.of(()->{
            String SQL = "SELECT ID, NAME, DESCRIPTION,BASE_PRICE ,TAX_RATE ,STATUS ,INVENTORY_QUANTITY FROM PRODUCTS ";
            List<Product> product= jdbcTemplate.query(SQL,new ProductMapper());
            return product;
        });
    }

    @Override
    public Try<Product> updateProduct(ProductId productId, ProductOperationRequest productOperationRequest) {
        return Try.of(() -> {
            String SQL = "UPDATE PRODUCTS SET NAME = ?,DESCRIPTION = ?, BASE_PRICE = ?,TAX_RATE = ?,STATUS=? ,INVENTORY_QUANTITY= ? WHERE ID=?  ";
            PreparedStatementCreator psc = connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL, Statement.NO_GENERATED_KEYS);
                ps.setString(1, productOperationRequest.getName().getValue());
                ps.setString(2, productOperationRequest.getDescription().getValue());
                ps.setBigDecimal(3, productOperationRequest.getBasePrice().getValue());
                ps.setBigDecimal(4, productOperationRequest.getTaxRate().getValue());
                ps.setString(5, productOperationRequest.getProductStatus().toString());
                ps.setInt(6, productOperationRequest.getInventoryQuantity().getValue());
                ps.setLong(7,productId.getValue());
                return  ps;
            };
            int value = jdbcTemplate.update(psc);
            if(value != 0){
                return productCreate(productId,productOperationRequest);
            }
            return null;
        });
    }

    private final Product productCreate(ProductId productId,ProductOperationRequest productOperationRequest){

        return Product.builder().id(productId)
                .name(productOperationRequest.getName())
                .description(productOperationRequest.getDescription())
                .basePrice(productOperationRequest.getBasePrice())
                .taxRate(productOperationRequest.getTaxRate())
                .productStatus(productOperationRequest.getProductStatus())
                .inventoryQuantity(productOperationRequest.getInventoryQuantity()).build();
    }

    @Override
    public Try<Product> deleteProduct(ProductId productId) {
        return Try.of(()->{
            String SQL = "DELETE FROM PRODUCTS WHERE ID = ?";
            Object[] objects = {productId.getValue()};
            int value= jdbcTemplate.update(SQL,objects);
            return null;
        });

    }

    @Override
    public Try<List<Product>> getProduct(Name name) {
        return Try.of(()->{
            String SQL = "SELECT ID, NAME, DESCRIPTION,BASE_PRICE ,TAX_RATE ,STATUS ,INVENTORY_QUANTITY FROM PRODUCTS WHERE NAME LIKE '%?%'";
            Object[] objects = {name.valueOf()};
            List<Product> product= jdbcTemplate.query(SQL,objects,new ProductMapper());
            return product;
        });
    }
}
