package lang.string;

import java.util.Arrays;

public class CharArrayMain {
    public static void main(String[] args) {
        char[] charArr = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
        System.out.println("charArr = " + Arrays.toString(charArr));

        String str = new String("hello");
        System.out.println("str = " + str);
    }
}
