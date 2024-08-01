package generic.test.ex3;

import generic.animal.Cat;
import generic.animal.Dog;

public class AnimalHospitalMainV3 {

    public static void main(String[] args) {
        AnimalHospitalV3<Dog> dogHospital = new AnimalHospitalV3();
        AnimalHospitalV3<Cat> catHospital = new AnimalHospitalV3();

        Dog dog = new Dog("멍멍이", 100);
        Cat cat = new Cat("냐옹이", 40);

        dogHospital.set(dog);
        catHospital.set(cat);
        dogHospital.checkup();
        catHospital.checkup();

        // Type safe -> 컴파일 에러
//        dogHospital.set(cat);

        dogHospital.set(dog);
        Dog biggerDog = dogHospital.bigger(new Dog("그냥 동물", 200));
        System.out.println("biggerDog = " + biggerDog);
    }

}
