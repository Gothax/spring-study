package generic.test.ex3;

import generic.animal.Dog;

public class DogHospital {

    private Dog dog;

    public void set(Dog dog) {
        this.dog = dog;
    }

    public void checkup(){
        System.out.println("dog.getName() = " + dog.getName());
        System.out.println("dog.getSize() = " + dog.getSize());
        dog.sound();
    }

    public Dog bigger(Dog target) {
        return dog.getSize() > target.getSize() ? dog : target;
    }
}
