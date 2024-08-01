package generic.ex1;

public class BoxMain3 {

    public static void main(String[] args) {

        GenericBox<Integer> integerBox = new GenericBox<>();
        integerBox.setValue(10);
        Integer intValue = integerBox.getValue();
        System.out.println(intValue);

        GenericBox<String> stringBox = new GenericBox<String>();
        stringBox.setValue("Hello");
        String stringValue = stringBox.getValue();
        System.out.println(stringValue);
    }
}
