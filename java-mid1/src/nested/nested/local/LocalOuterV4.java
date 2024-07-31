package nested.nested.local;

import java.lang.reflect.Field;

public class LocalOuterV4 {

    private int outInstanceVar = 3;

    public Printer process(int paramVar) {
        int localVar = 1; // 지역변수는 스택 영역이 종료되는 순간 함꼐 제거된다

        class LocalPrinter implements Printer{
            int value = 0;

            @Override
            public void print() {
                System.out.println("value = " + value);
                System.out.println("localVar = " + localVar);
                System.out.println("paramVar = " + paramVar);
                System.out.println("outInstanceVar = " + outInstanceVar);

            }
        }

        //        localPrinter.print(); // 실행하지 않고 Printer 인스턴스 반환
        return new LocalPrinter();
    }

    public static void main(String[] args) {
        LocalOuterV4 localouterV3 = new LocalOuterV4();
        Printer printer = localouterV3.process(2);
        printer.print();

        System.out.println("필드 확인");
        Field[] declaredFields = printer.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("declaredField = " + declaredField);
        }
    }
}
