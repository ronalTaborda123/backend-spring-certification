package co.com.ias.certification.backend.orders.order.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import io.vavr.control.Try;

import co.com.ias.certification.backend.orders.order.domain.*;
import co.com.ias.certification.backend.orders.order.port.out.CreateOrderPort;
import co.com.ias.certification.backend.orders.order.port.out.DeleteOrdersPort;

public class SqlOrderRepository implements CreateOrderPort, DeleteOrdersPort
{

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public SqlOrderRepository(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }


    @Override
    public Try<Order> createOrder(OrderOperationRequest orderOperationRequest) {
        return Try.of(() -> {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("client",orderOperationRequest.getClient().getValue());
            parameters.put("total",orderOperationRequest.getTotal().getValue());
            parameters.put("descuento",orderOperationRequest.getDescuento().getValue());
            parameters.put("orderStatus",orderOperationRequest.getOrderStatus().name());
            Number number=0;

            number = simpleJdbcInsert.execute(parameters);
            String SQL = "SELECT * FROM ORDERS ORDER BY IDORDERS DESC LIMIT 1";
            return jdbcTemplate.queryForObject(SQL,rowMapper);
        });
    }

    @Override
    public Try<Order> deleteOrder(OrdersId ordersId) {
        return null;
    }
    private final RowMapper<Order> rowMapper = (resultSet, i) -> {
//        OrdersId ordersId= OrdersId.of();
//        Client client=Client.of(resultSet.getString("CLIENT"));
//        Total total= Total.of(resultSet.getBigDecimal("TOTAL"));
//        Descuento descuento=Descuento.of(resultSet.getBigDecimal("DISCOUNT"));
//        OrderStatus orderStatus=OrderStatus.valueOf(resultSet.getString("STATUS"));

        return Order.builder()
                .ordersId(OrdersId.of(resultSet.getLong("IDORDERS")))
                .client(Client.of(resultSet.getString("CLIENT")))
                .total(Total.of(resultSet.getBigDecimal("TOTAL")))
                .descuento(Descuento.of(resultSet.getBigDecimal("DISCOUNT")))
                .orderStatus(OrderStatus.valueOf(resultSet.getString("STATUS"))).build();
    };
}
