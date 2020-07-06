package co.com.ias.certification.backend.serialization;

import java.lang.reflect.Type;
import java.util.function.Function;

import com.google.gson.*;


public class NumberValueAdapter <T extends NumberSerializable> implements JsonSerializer<T>, JsonDeserializer {
    private final Function<Number, T> factory;

    public NumberValueAdapter(Function<Number, T> factory) {
        this.factory = factory;
    }

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        Number value = src.valueOf();
        return new JsonPrimitive(value);
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Number value = json.getAsNumber();
        return factory.apply(value);
    }


}

