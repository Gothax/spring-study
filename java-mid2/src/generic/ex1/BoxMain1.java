package generic.ex1;

public class BoxMain1 {

    public static void main(String[] args) {
        IntegerBox intBox = new IntegerBox();
        intBox.set(10);
        Integer i = intBox.get();
        System.out.println("i = " + i);

        StringBox stringBox = new StringBox();
        stringBox.setValue("hello");
        String value = stringBox.getValue();
        System.out.println("value = " + value);
    }
}
