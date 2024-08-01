package generic.test.ex4;

import generic.animal.Animal;

public class ComplexBox <T extends Animal>{

    private T animal;

    public void set(T animal) {
        this.animal = animal;
    }

    public <T> T printAndReturn(T t) {
        System.out.println("animal = " + animal.getClass().getName());
        System.out.println("t.getClass().getName() = " + t.getClass().getName());
        return t;
    }

}
