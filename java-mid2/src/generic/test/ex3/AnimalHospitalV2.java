package generic.test.ex3;

import generic.animal.Animal;

public class AnimalHospitalV2<T> {

    private T animal;

    public void set(T animal) {
        this.animal = animal;
    }

    public void checkup() {
//        System.out.println("animal.getName() = " + animal.getName());
//        System.out.println("animal.getName() = " + animal.getName());
//        animal.sound();
    }

    public T bigger(T target) {
//        return animal.getSize() > target.getSize() ? animal : target;
        return null;
    }


}
