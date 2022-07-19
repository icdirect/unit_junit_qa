package guru.qa;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JunitCore {

    public static void main(String[] args) throws Exception {
        // найти классы с аннотацией @Test

        Class clazz = SimpleTest.class;
        // в этом цикле запускаем все метода
        for (Method method : clazz.getDeclaredMethods()) {
            Test methodAnnotation = method.getAnnotation(Test.class);
            if (methodAnnotation != null) {
                //запускаем метод @Test
                try {
                    method.invoke(clazz.getConstructor().newInstance());
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.println("test failed: " + method.getName());
                        continue;
                    } else {
                        System.out.println("test broken: " + method.getName());
                        continue;
                    }
                }
                System.out.println("test passed: " + method.getName());
        }
        }
        // запустить все методы @Test

        // вывести результаты
    }
}
