package com.qworks.billingengine.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.Resource;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JsonUtils() {

    }

    public static <T> T deserialize(byte[] data, Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(data, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserialize(String data, Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(data.getBytes(StandardCharsets.UTF_8), type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserialize(Map data, Class<T> type) {
        try {
            T obj = OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsBytes(data), type);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserialize(Resource resource, Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(resource.getInputStream(), type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static<K,V> Map<K,V> toMap(Object obj) {
        return OBJECT_MAPPER.convertValue(obj, Map.class);
    }


    public static final String toJsonString(Object obj) {
        ObjectWriter objectWriter = OBJECT_MAPPER.writer();
        String json = null;
        try {
            json = objectWriter.withDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    @SuppressWarnings("unchecked")
    public static final Map<String, Object> cloneMap(Map<String, Object> src) {
        Map<String, Object> copyMap = new HashMap<>();
        src.forEach((k, v) -> {
            if (null != v)
                if (Map.class.isAssignableFrom(v.getClass()))
                    copyMap.put(k, cloneMap((Map<String, Object>) v));
                else
                    copyMap.put(k, v);
        });
        return copyMap;
    }

    
    public static void serialize(Object object, OutputStream outputStream)  {
        try {
            OBJECT_MAPPER.writeValue(outputStream, object);
        } catch (IOException e) {
            throw new RuntimeException("Unable to deserialize", e);
        }
    }

    public static <T> T convert(Object src, TypeReference<T> typeReference) {
        return OBJECT_MAPPER.convertValue(src, typeReference);
    }

    public static <T> T readFile(File file, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(file, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(
                String.format(
                    "Failed to read json from file:%s with error:%s"
                    , file.getAbsolutePath()
                    , e.getMessage()
                )
                , e
            );
        }
    }

    public static <T> T convert(Object src, Class<T> type) {
        return OBJECT_MAPPER.convertValue(src, type);
    }

   
}
