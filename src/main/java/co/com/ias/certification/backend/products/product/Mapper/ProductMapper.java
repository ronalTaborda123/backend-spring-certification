package co.com.ias.certification.backend.products.product.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.ias.certification.backend.products.product.domain.*;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {

        return Product.builder().
                id(ProductId.of(resultSet.getLong("ID")))
                .name(Name.of(resultSet.getString("NAME")))
                .description(Description.of(resultSet.getString("DESCRIPTION")))
                .basePrice(BasePrice.of(resultSet.getBigDecimal("BASE_PRICE")))
                .taxRate(TaxRate.of(resultSet.getBigDecimal("TAX_RATE")))
                .productStatus(ProductStatus.valueOf(resultSet.getString("STATUS")))
                .inventoryQuantity(InventoryQuantity.of(resultSet.getInt("INVENTORY_QUANTITY"))).build();
    }
}
