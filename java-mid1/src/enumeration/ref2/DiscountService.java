package enumeration.ref2;

public class DiscountService {

    public int discount(Grade grade, int price) {
        return grade.getDiscountPercent() * price / 100;
    }
}
