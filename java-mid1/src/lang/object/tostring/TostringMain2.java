package lang.object.tostring;

public class TostringMain2 {

    public static void main(String[] args) {
        Dog dog1 = new Dog("dog1", 12);
        Dog dog2 = new Dog("dog2", 13);
        Car car = new Car("model y");

        System.out.println("car.toString() = " + car.toString());
        System.out.println("dog1.toString() = " + dog1.toString());
        System.out.println("dog2.toString() = " + dog2.toString());

        System.out.println("car.toString() = " + car);
        System.out.println("dog1.toString() = " + dog1);
        System.out.println("dog2.toString() = " + dog2);
    }
}
