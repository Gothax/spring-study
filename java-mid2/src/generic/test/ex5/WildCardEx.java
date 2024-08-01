package generic.test.ex5;

import generic.animal.Animal;

public class WildCardEx {

    static <T> void printGenericV1(Box<T> box) {
        System.out.println("box.get() = " + box.get());
    }

    static void printWildCardV1(Box<?> box) {
        System.out.println("box.get() = " + box.get());
    }

    static <T extends Animal> void printGenericV2(Box<T> box) {
        T t = box.get();
        System.out.println("t.getName() + t.getSize() = " + t.getName() + t.getSize());
    }

    static void printWildCardV2(Box<? extends Animal> box) {
        Animal animal = box.get();
        System.out.println("animal.getName() + animal.getSize() = " + animal.getName() + animal.getSize());
    }

    static <T extends Animal> T printAndReturnGeneric(Box<T> box) {
        T t = box.get();
        System.out.println("t.getName() = " + t.getName());
        return t;
    }

    // 이런 상황에서 wildcard가 해결하기 힘들기 떄문에 제너릭 타입 메서드를 사용한다!
    // return type을 dog/cat이 아닌 animal로 반환
    static Animal printAndReturnWildracrd(Box<? extends Animal> box) {
        Animal animal = box.get();
        System.out.println("animal = " + animal.getName());
        return animal;
    }




}
