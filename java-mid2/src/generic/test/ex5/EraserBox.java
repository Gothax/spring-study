package generic.test.ex5;

public class EraserBox<T> {

    public boolean instanceCheck(Object param) {
        // 항상 Object와 비교하기 떄문에 항상 참이게 된다!
//        return param instanceof T;
        return false;
    }
}
