package lang.clazz;

import java.lang.reflect.InvocationTargetException;

public class ClassCreateMain {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class helloClass = Hello.class;
        Class<?> aClass = Class.forName("lang.clazz.Hello");

        Hello hello = (Hello) helloClass.getDeclaredConstructor().newInstance();
        String result = hello.hello();
        System.out.println("result = " + result);

    }
}
