package lang.immutable.address;

public class RefMain2 {

    // 참조형은 하나의 인스턴스를 공유
    public static void main(String[] args) {
        ImmutableAddress a = new ImmutableAddress("서울");
        ImmutableAddress b = a;

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        b = new ImmutableAddress("A");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

}
