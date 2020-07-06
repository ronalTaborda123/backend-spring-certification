package co.com.ias.certification.backend.serialization;

import java.lang.reflect.Type;

import io.vavr.control.Try;

import com.google.gson.*;

public class TryValueAdapter <T> implements JsonSerializer<Try<T>> {
    @Override
    public JsonElement serialize(Try<T> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject response = new JsonObject();
        if (src.isSuccess()) {
            response.add("data", context.serialize(src.get()));
        } else {
            Throwable cause = src.getCause();
            response.add("error", new JsonPrimitive(cause.getMessage()));
        }
        return response;
    }
}
