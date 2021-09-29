package com.balzzak.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtil {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Object data) {
        String str = gson.toJson(data);
        return str;
    }

    public static <T> T fromJson(String json) {
        return gson.fromJson(json, TypeToken.getParameterized(Object.class).getType());
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> List<T> fromJsonToList(String json) {
        return gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
    }

    //public static <T> List<T> fromJsonToList(String json, Class<T> typeClass) {
    //    Type typeOfT = TypeToken.getParameterized(List.class, typeClass).getType();
    //    return gson.fromJson(json, typeOfT);
    //}

    // reference : https://stackoverflow.com/questions/5554217/google-gson-deserialize-listclass-object-generic-type
    public static <T> List<T> fromJsonToList(String json, Class<T> typeClass) {
        return gson.fromJson(json, new ListOfJson<T>(typeClass));
    }

    static class ListOfJson<T> implements ParameterizedType {
        private Class<?> wrapped;

        public ListOfJson(Class<T> wrapper) {
            this.wrapped = wrapper;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{wrapped};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}


