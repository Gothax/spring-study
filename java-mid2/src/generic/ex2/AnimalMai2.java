package generic.ex2;

import generic.animal.Animal;
import generic.animal.Cat;
import generic.animal.Dog;

public class AnimalMai2 {

    public static void main(String[] args) {
        Animal animal = new Animal("동물", 0);
        Dog dog = new Dog("멍멍이", 100);
        Cat cat = new Cat("냐옹이", 40);


        Box<Animal> animalBox = new Box<>();
        animalBox.set(dog);
        animalBox.set(cat);

        Animal animal1 = animalBox.get();
        System.out.println("animal1 = " + animal1);
    }
}
