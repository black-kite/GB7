import ourAnnotations.AfterSuite;
import ourAnnotations.BeforeSuite;
import ourAnnotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class Task7ForExample {

    public static void main(String[] args) {
        try {
            start(Task7ForExample.class);
            Task7ForExample.start(Test1Class.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void start(Class<?> aClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Method[] methods = aClass.getDeclaredMethods();
        ArrayList<Method> list = new ArrayList<>();
        for(Method metod : methods){
            if(metod.isAnnotationPresent(Test.class)){
                int priority = metod.getAnnotation(Test.class).priority();
                if(priority<1 || priority>10) {
                    throw new RuntimeException("Значение приоритета: " + priority + " в методе "+metod.getName()+" недопустимо!");
                }
                list.add(metod);
            }
        }
        list.sort(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o1.getAnnotation(Test.class).priority() - o2.getAnnotation(Test.class).priority();
            }
        });

        boolean isBefore = false;
        boolean isAfter = false;

        for(Method metod : methods){
            if(metod.isAnnotationPresent(BeforeSuite.class)){

                if(!isBefore) {
                    list.add(0, metod);
                    isBefore = true;
                } else{
                    throw new RuntimeException("Анотация " + metod.getAnnotation(BeforeSuite.class) + " в классе уже есть");
                }
            }
            if(metod.isAnnotationPresent(AfterSuite.class)){

                if(!isAfter) {
                    list.add(metod);
                    isAfter = true;
                } else{
                    throw new RuntimeException("Анотация " + metod.getAnnotation(AfterSuite.class) + " в классе уже есть");
                }
            }

        }
        Object object = aClass.newInstance();
        for(Method m: list){
            m.invoke(object);
        }

    }
}
