package generic.ex2;

import generic.animal.Animal;
import generic.animal.Cat;

public class AnimalMain {

    public static void main(String[] args) {
        Animal animal = new Animal("동물", 0);
        C dog = new C("멍멍이", 100);
        Cat cat = new Cat("냐옹이", 40);

        Box<C> dogBox = new Box<>();
        dogBox.set(dog);
        C findDog = dogBox.get();
        System.out.println("findDog = " + findDog);

        Box<Cat> catBox = new Box<>();
        catBox.set(cat);
        Cat findCat = catBox.get();
        System.out.println("findCat = " + findCat);

        Box<Animal> animalBox = new Box<>();
        animalBox.set(animal);
        Animal findAnimal = animalBox.get();
        System.out.println("findAnimal = " + findAnimal);
    }
}
