package nested.nested.test;

public class AnonymousMainRef {

    public static void main(String[] args) {
         Hello hello = () -> System.out.println("AnonymousMain.hello");
         hello.hello();
    }
}
