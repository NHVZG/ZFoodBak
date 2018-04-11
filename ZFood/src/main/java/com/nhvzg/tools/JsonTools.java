package com.nhvzg.tools;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by NHVZG on 2018/2/27.
 */
public class JsonTools{
    private static ObjectMapper mapper=new ObjectMapper();
    public static<T> T GetObject(String json, Class<T> t) throws IOException {
        return mapper.readValue(json,t);
    }

    public static <T> List GetList(String json, Class<T> t) throws IOException {
        return mapper.readValue(json,  mapper.getTypeFactory().constructParametricType(List.class, t));
    }

    public static String GetJson(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o);
    }

    public static  <T>List GetPartObjectToList(String json,Class<T> t,String partName) throws IOException {
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(json);
        ObjectNode root = mapper.readTree(parser);
        JsonNode node=root.get(partName);
        return GetList(node.toString(),t);
    }

    public static<T> T GetPartObjectToObject(String json,Class<T> t,String partName) throws IOException {
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(json);
        ObjectNode root = mapper.readTree(parser);
        JsonNode node=root.get(partName);
        return GetObject(node.toString(),t);
    }
}
