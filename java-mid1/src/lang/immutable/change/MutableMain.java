package lang.immutable.change;

public class MutableMain {

    public static void main(String[] args) {
        MutableObj obj = new MutableObj(10);
        obj.add(10);
        System.out.println("obj = " + obj.getValue());
    }
}
