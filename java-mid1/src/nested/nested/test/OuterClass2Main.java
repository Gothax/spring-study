package nested.nested.test;

import nested.nested.inner.InnerOuter;

public class OuterClass2Main {
    public static void main(String[] args) {
        OuterClass2 o = new OuterClass2();
        OuterClass2.InnerClass inner= o.new InnerClass();
        inner.hello();
    }
}