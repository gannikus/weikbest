package com.weikbest.pro.saas.common.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.io.IOException;

/**
 * @author wisdomelon
 * @date 2019/6/11 0011
 * @project saas
 * @jdk 1.8
 */
public class JsonMapper {

    private ObjectMapper objectMapper = new ObjectMapper();

    public JsonMapper() {

        // 默认格式
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));

        // 允许空值转换
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        // 转义字符
        objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        // null -> ""
        objectMapper.getSerializerProvider().setNullKeySerializer(new JsonSerializer<Object>() {

            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                jsonGenerator.writeString("");
            }
        });
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /***
     * 将bean对象转换为json字符串，
     * 如果某些属性不需要转换json，可以在bean的java类上加上注解@JsonIgnoreProperties({"field1"},{"field2"}...)
     * @param o
     * @return
     * @throws JsonProcessingException
     */
    public String bean2Json(Object o) throws JsonProcessingException {
        if (o == null) {
            throw new IllegalArgumentException("object is null while write the Json string...");
        }
        return objectMapper.writeValueAsString(o);
    }

    /***
     * json字符串转为bean对象
     *
     * @param jsonString
     * @param c
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T json2Bean(String jsonString, Class<T> c) throws IOException {
        return objectMapper.readValue(jsonString, c);
    }

    /***
     * json字符串转为集合对象
     * @param jsonString
     * @param collectionClass
     * @param elementClass
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T json2Bean(String jsonString, Class<?> collectionClass, Class<?>... elementClass) throws IOException {
        return objectMapper.readValue(jsonString, createCollectionType(collectionClass, elementClass));
    }

    /***
     * json字符串转换为bean对象
     * @param jsonString
     * @param javaType
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T json2Bean(String jsonString, JavaType javaType) throws IOException {
        return objectMapper.readValue(jsonString, javaType);
    }

    /***
     * 创建集合的类型
     * @param collectionClass
     * @param elementClass
     * @return
     */
    private JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClass) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
    }
}
