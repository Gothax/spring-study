package lang.object.poly;

public class ObjectPolyExample1 {

    public static void main(String[] args) {
        Dog dog = new Dog();
        Car car = new Car();
//        dog.sound();
//        car.sound();
        action(dog);
        action(car);
    }

    private static void action(Object object) {
        String string = object.toString();
        System.out.println(string);
        if (object instanceof Dog dog) {
            dog.sound();
        }
        else if (object instanceof Car car) {
            car.sound();
        }
    }
}
