package co.com.ias.certification.backend.configuration;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.vavr.control.Try;

import co.com.ias.certification.backend.products.product.domain.*;
import co.com.ias.certification.backend.serialization.NumberValueAdapter;
import co.com.ias.certification.backend.serialization.StringValueAdapter;
import co.com.ias.certification.backend.serialization.TryValueAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class GsonConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(ProductId.class, new NumberValueAdapter<>(ProductId::fromNumber))
                .registerTypeAdapter(TaxRate.class, new NumberValueAdapter<>(TaxRate::fromNumber))
                .registerTypeAdapter(BasePrice.class, new NumberValueAdapter<>(BasePrice::fromNumber))
                .registerTypeAdapter(Name.class, new StringValueAdapter<>(Name::of))
                .registerTypeAdapter(Description.class, new StringValueAdapter<>(Description::of))
                .registerTypeAdapter(InventoryQuantity.class, new NumberValueAdapter<>(InventoryQuantity::fromNumber))
                .registerTypeAdapter(Try.class, new TryValueAdapter<>())
                .create();

    }
}
