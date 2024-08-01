package generic.test.ex3;

import generic.animal.Cat;

public class CatHospital {

    private Cat dog;

    public void set(Cat dog) {
        this.dog = dog;
    }

    public void checkup(){
        System.out.println("dog.getName() = " + dog.getName());
        System.out.println("dog.getSize() = " + dog.getSize());
        dog.sound();
    }

    public Cat bigger(Cat target) {
        return dog.getSize() > target.getSize() ? dog : target;
    }
}
