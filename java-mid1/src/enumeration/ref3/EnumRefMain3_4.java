package enumeration.ref3;


import static enumeration.ref3.Grade.*;

public class EnumRefMain3_4 {

    public static void main(String[] args) {
        int price = 10_000;
        Grade[] values = values();
        for (Grade value : values) {
            printDiscount(value, price);
        }
    }

    private static void printDiscount(Grade grade, int price) {
        System.out.println("grade.discount(price) = " + grade.discount(price));
    }
}
