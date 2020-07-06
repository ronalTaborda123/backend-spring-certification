package co.com.ias.certification.backend.configuration.domain;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class WebApplicationConfiguration implements WebMvcConfigurer {

    private final Gson gson;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter(gson);
        converters.add(gsonHttpMessageConverter);
    }

}
