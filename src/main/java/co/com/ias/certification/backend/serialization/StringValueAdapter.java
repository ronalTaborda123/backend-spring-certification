package co.com.ias.certification.backend.serialization;

import java.lang.reflect.Type;
import java.util.function.Function;

import com.google.gson.*;

public class StringValueAdapter <T extends StringSerializable> implements JsonSerializer<T>, JsonDeserializer<T> {
    private final Function<String, T> factory;

    public StringValueAdapter(Function<String, T> factory) {
        this.factory = factory;
    }


    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String value = json.getAsString();
        return factory.apply(value);
    }

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        String value = src.valueOf();
        return new JsonPrimitive(value);
    }
}

