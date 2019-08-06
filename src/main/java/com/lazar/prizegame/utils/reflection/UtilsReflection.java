package com.lazar.prizegame.utils.reflection;

import lombok.experimental.UtilityClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@UtilityClass
@SuppressWarnings("HideUtilityClassConstructor")
public class UtilsReflection {

    public static Class getParameterClazzOfObject(Object object) {
        return getParameterClazzOfObject(object, 0);
    }

    public static Class getParameterClazzOfObject(Object object, int parameter) {

        Type mySuperclass = object.getClass().getGenericSuperclass();

        return getParameterClazzOfType(mySuperclass, parameter);
    }

    public static Class getParameterClazzOfType(Type type) {
        return getParameterClazzOfType(type, 0);
    }

    public static Class getParameterClazzOfType(Type type, int parameter) {

        if (type instanceof ParameterizedType) {

            Type tType = ((ParameterizedType) type).getActualTypeArguments()[parameter];
            String[] split = tType.toString().split(" ");

            String className = split.length > 0 ? split[1] : split[0];

            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
            }

        }

        return null;
    }

    public static Method getDeclaredMethod(Class clazz, String method, Class... args) throws NoSuchMethodException {

        try {

            return clazz.getDeclaredMethod(method, args);

        } catch (NoSuchMethodException e) {

            if (!clazz.getSuperclass().equals(Object.class)) {
                return getDeclaredMethod(clazz.getSuperclass(), method, args);
            }

        }

        throw new NoSuchMethodException();
    }

    public static Field getDeclaredField(Class clazz, String field) {

        if (!clazz.equals(Object.class)) {

            Field[] fields = clazz.getDeclaredFields();

            for (Field f : fields) {
                if (f.getName().equals(field)) {
                    return f;
                }
            }

            return getDeclaredField(clazz.getSuperclass(), field);
        }

        return null;
    }

    public static boolean hasGivenSuperclass(Class clazz, Class superclass) {

        if (!clazz.equals(Object.class)) {

            if (clazz.getSuperclass().equals(superclass)) {
                return true;
            }
            return hasGivenSuperclass(clazz.getSuperclass(), superclass);
        }

        return false;
    }

    public static final <T extends Annotation> T getDelegateAnnotation(Class<T> annotationClass, Method delegateMethod) {
        T annotation = delegateMethod.getAnnotation(annotationClass);
        if (annotation == null) {
            annotation = delegateMethod.getDeclaringClass().getAnnotation(annotationClass);
        }
        return annotation;
    }

}

