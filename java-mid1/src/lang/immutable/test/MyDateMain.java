package lang.immutable.test;

public class MyDateMain {


    public static void main(String[] args) {
        MyDate myDate = new MyDate(2024, 1, 1);
        MyDate myDate2 = new MyDate(2024, 1, 1);
        System.out.println("myDate = " + myDate);
        System.out.println("myDate2 = " + myDate2);

        myDate.setYear(2025);
        System.out.println("myDate = " + myDate);
        System.out.println("myDate2 = " + myDate2);
    }
}
