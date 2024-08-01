package generic.test.ex4;

import generic.animal.Cat;
import generic.animal.Dog;

public class MethodMain3 {


    public static void main(String[] args) {
        Dog dog = new Dog("멍멍이", 100);
        Cat cat = new Cat("냐옹이", 20);

        ComplexBox<Dog> hospital = new ComplexBox<>();
        hospital.set(dog);

        // 제너릭 타입은 메서드가 우선
        // 즉 generic class type과, method에서 정의한 타입은 무관(같은 T t 임에도)
        Cat returnCat = hospital.printAndReturn(cat);
        System.out.println("returnCat = " + returnCat);
    }

}
