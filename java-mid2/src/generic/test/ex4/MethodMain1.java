package generic.test.ex4;

public class MethodMain1 {

    public static void main(String[] args) {
        Integer i = 10;
        Object result = GenericMethod.objectMethod(i);
        Integer resultInteger = (Integer) GenericMethod.objectMethod(i);

        System.out.println("명시적 타입 전달");
        Integer resultGeneric = GenericMethod.genericMethod(i);
        System.out.println("resultGeneric = " + resultGeneric);

        Integer integerResult = GenericMethod.numberMethod(10);
    }
}
