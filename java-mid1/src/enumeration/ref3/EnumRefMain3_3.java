package enumeration.ref3;


import static enumeration.ref3.Grade.*;

public class EnumRefMain3_3 {

    public static void main(String[] args) {
        int price = 10_000;
        DiscountService discountService = new DiscountService();
        printDiscount(BASIC, price);
        printDiscount(GOLD, price);
        printDiscount(DIAMOND, price);
    }

    private static void printDiscount(Grade grade, int price) {
        System.out.println("grade.discount(price) = " + grade.discount(price));
    }
}
