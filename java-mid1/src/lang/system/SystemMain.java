package lang.system;

import java.util.Map;

public class SystemMain {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println("l = " + l);

        long l1 = System.nanoTime();
        System.out.println("l1 = " + l1);

        Map<String, String> getenv = System.getenv();
        System.out.println("getenv = " + getenv);
    }
}
