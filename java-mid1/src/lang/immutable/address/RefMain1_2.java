package lang.immutable.address;

public class RefMain1_2 {

    // 참조형은 하나의 인스턴스를 공유
    public static void main(String[] args) {
        Address a = new Address("서울");
        Address b = new Address("서울");

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        b.setValue("부산");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

}
