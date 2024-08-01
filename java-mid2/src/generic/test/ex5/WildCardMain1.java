package generic.test.ex5;

import generic.animal.Cat;
import generic.animal.Dog;

public class WildCardMain1 {

    public static void main(String[] args) {
        Box<Object> objectBox = new Box<>();
        Box<Dog> dogBox = new Box<>();
        Box<Cat> catBox = new Box<>();

        dogBox.set(new Dog("멍멍이", 100));
        WildCardEx.printGenericV1(dogBox);
        WildCardEx.printGenericV2(dogBox);
        Dog dog = WildCardEx.printAndReturnGeneric(dogBox);

        WildCardEx.printWildCardV1(dogBox);
        WildCardEx.printWildCardV2(dogBox);
        WildCardEx.printAndReturnWildracrd(dogBox);
    }


}
