package com.gbtest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StartTest {
    public static void start(Class c) {
        List<Method> methods = new ArrayList<>();
        Method[] classMethods = c.getDeclaredMethods();

        for (Method m : classMethods) {
            if (m.isAnnotationPresent(com.gbtest.Test.class)) {
                methods.add(m);
            }
        }

//        methods.sort(Comparator.comparingInt((Method i) -> {
////            return i.getAnnotation(Test.class).priority();
////        }).reversed());

        methods.sort(Comparator
                .comparingInt((Method i) -> i.getAnnotation(com.gbtest.Test.class).priority())
                .reversed());


        for (Method m : classMethods) {
            if (m.isAnnotationPresent(com.gbtest.BeforeSuite.class)) {
                if (methods.size() > 0 && methods.get(0).isAnnotationPresent(com.gbtest.BeforeSuite.class)) {
                    throw new RuntimeException("@BeforeSuite annotation method > 1");
                }
                methods.add(0, m);
            }
        }

        for (Method m : classMethods) {
            if (m.isAnnotationPresent(com.gbtest.AfterSuite.class)) {
                if (methods.size() > 0 && methods.get(methods.size() - 1).isAnnotationPresent(com.gbtest.AfterSuite.class)) {
                    throw new RuntimeException("@AfterSuite annotation method > 1");
                }
                methods.add(m);
            }
        }

        for (Method m : methods) {
            try {
                m.invoke(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }
}
