package lang.immutable.test;

public class MyDateMainImmutable {


    public static void main(String[] args) {
        MyDateImmutable myDate = new MyDateImmutable(2024, 1, 1);
        MyDateImmutable myDate2 = new MyDateImmutable(2024, 1, 1);
        System.out.println("myDate = " + myDate);
        System.out.println("myDate2 = " + myDate2);

        myDate = myDate.withYear(2025);
        System.out.println("myDate = " + myDate);
        System.out.println("myDate2 = " + myDate2);
    }
}
