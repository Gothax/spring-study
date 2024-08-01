package generic.ex1;

import javax.swing.*;

public class BoxMain2 {

    public static void main(String[] args) {
        ObjectBox intBox = new ObjectBox();
        intBox.setValue(10);
        Integer value =  (Integer) intBox.getValue();
        System.out.println("value = " + value);

        ObjectBox stringBox = new ObjectBox();
        stringBox.setValue("hello");
        String str = (String) stringBox.getValue();
        System.out.println("str = " + str);
    }
}
