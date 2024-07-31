package lang.wrapper;

public class WrapperClassMain {

    public static void main(String[] args) {
        Integer i = new Integer(10);
        Integer i1 = Integer.valueOf(10);
        Integer i2 = 0;
        int i3 = 10;
        System.out.println("i = " + i);
        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);

        String string = i1.toString();
        System.out.println("string = " + string);
        System.out.println("i3 = " + i3);

        boolean equals = i2.equals(0);
        System.out.println("equals = " + equals);
    }
}
