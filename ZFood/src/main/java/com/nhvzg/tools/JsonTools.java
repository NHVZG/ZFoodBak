package com.nhvzg.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
}
