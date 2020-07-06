package co.com.ias.certification.backend.products.product.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import io.vavr.control.Try;
import co.com.ias.certification.backend.products.product.domain.*;
import co.com.ias.certification.backend.products.product.port.out.CreateProductPort;
import co.com.ias.certification.backend.products.product.port.out.DeleteProductPort;
import co.com.ias.certification.backend.products.product.port.out.ListProductPort;
import co.com.ias.certification.backend.products.product.port.out.UpdateProductPort;



public class SqlProductRepository implements CreateProductPort, ListProductPort , UpdateProductPort , DeleteProductPort {

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
        return jdbcTemplate.queryForObject(SQL,rowMapper);
        });
//        /return Product.of(productCreate(ProductId.of(product.getProductId().getValue()),productOperationRequest));
    }

    private final RowMapper<Product> rowMapper = (resultSet, i) -> {

//        ProductId id1 = ProductId.of(resultSet.getLong("ID"));
//        Name name = Name.of(resultSet.getString("NAME"));
//        Description description = Description.of(resultSet.getString("DESCRIPTION"));
//        BasePrice basePrice = BasePrice.of(resultSet.getBigDecimal("BASE_PRICE"));
//        TaxRate taxRate = TaxRate.of(resultSet.getBigDecimal("TAX_RATE"));
//        ProductStatus productStatus = ProductStatus.valueOf(resultSet.getString("STATUS"));
//        InventoryQuantity inventoryQuantity = InventoryQuantity.of(resultSet.getInt("INVENTORY_QUANTITY"));

//        return Product.of(id1, name, description,basePrice,taxRate,productStatus,inventoryQuantity);
        return Product.builder().
                id(ProductId.of(resultSet.getLong("ID")))
                .name(Name.of(resultSet.getString("NAME")))
                .description(Description.of(resultSet.getString("DESCRIPTION")))
                .basePrice(BasePrice.of(resultSet.getBigDecimal("BASE_PRICE")))
                .taxRate(TaxRate.of(resultSet.getBigDecimal("TAX_RATE")))
                .productStatus(ProductStatus.valueOf(resultSet.getString("STATUS")))
                .inventoryQuantity(InventoryQuantity.of(resultSet.getInt("INVENTORY_QUANTITY"))).build();
    };

    @Override
    public Try<List<Product>>getListProduct() {

        return Try.of(()->{
            String SQL = "SELECT ID, NAME, DESCRIPTION,BASE_PRICE ,TAX_RATE ,STATUS ,INVENTORY_QUANTITY FROM PRODUCTS ";
            List<Product> product= jdbcTemplate.query(SQL,rowMapper);
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
}
