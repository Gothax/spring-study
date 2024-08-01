package generic.test.ex4;

import generic.animal.Animal;
import generic.animal.Cat;
import generic.animal.Dog;

public class MethodMain2 {

    public static void main(String[] args) {

        Dog dog = new Dog("몽뭉이", 100);
        Cat cat = new Cat("뚱냥이", 120);

        AnimalMethod.checkup(dog);
        Dog newDog = new Dog("뚱뚱강아지", 300);
        Dog biggerDog = AnimalMethod.bigger(newDog, dog);
        System.out.println("AnimalMethod.bigger(newDog, dog) = " + AnimalMethod.bigger(newDog, dog));

        // ??? 타입이 다를때 자동으로 업캐스팅 해주네
        Animal biggerResult = AnimalMethod.bigger(dog, cat);
        System.out.println("biggerResult = " + biggerResult);
    }
}
