package com.Days.JWTAuth.Utility;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelUtility {
    public static <T> T convertToModel(Map<String, Object> fieldsMap, Class<T> modelClass) throws IllegalAccessException, InstantiationException {
        T model = modelClass.newInstance();

        for (Map.Entry<String, Object> entry : fieldsMap.entrySet()) {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();

            Field field = getField(modelClass, fieldName);
            if (field != null) {
                field.setAccessible(true);
                setFieldValue(field, model, fieldValue);
            }
        }

        return model;
    }

    private static Field getField(Class<?> modelClass, String fieldName) {
        try {
            return modelClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            if (modelClass.getSuperclass() != null) {
                return getField(modelClass.getSuperclass(), fieldName);
            }
            return null;
        }
    }

    private static void setFieldValue(Field field, Object object, Object fieldValue) throws IllegalAccessException, InstantiationException {
        if (fieldValue == null) {
            field.set(object, null);
        } else if (field.getType().isAssignableFrom(fieldValue.getClass())) {
            field.set(object, fieldValue);
        } else if (field.getType().isEnum() && fieldValue instanceof String) {
            Enum<?> enumValue = Enum.valueOf((Class<Enum>) field.getType(), (String) fieldValue);
            field.set(object, enumValue);
        } else if (field.getType().equals(List.class) && fieldValue instanceof List) {
            List<?> fieldValueList = (List<?>) fieldValue;
            Class<?> genericType = getGenericType(field);
            List<Object> modelList = new ArrayList<>();

            for (Object item : fieldValueList) {
                if (item instanceof Map) {
                    Object modelItem = convertToModel((Map<String, Object>) item, genericType);
                    modelList.add(modelItem);
                }
            }

            field.set(object, modelList);
        } else if (fieldValue instanceof Map && !field.getType().isPrimitive()) {
            Object nestedModel = convertToModel((Map<String, Object>) fieldValue, field.getType());
            field.set(object, nestedModel);
        }
    }

    private static Class<?> getGenericType(Field field) {
        if (field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            return (Class<?>) parameterizedType.getActualTypeArguments()[0];
        }
        return null;
    }
}
