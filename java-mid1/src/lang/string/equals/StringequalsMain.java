package lang.string.equals;

public class StringequalsMain {

    public static void main(String[] args) {
//        String str1 = "hello";
//        String str2 = "hello";
//
        String str1 = new String("hello");
        String str2 = new String("hello");

        System.out.println("(str1==str2) = " + (str1 == str2));
        System.out.println("(str1==str2) = " + (str1.equals(str2)));

        String str3 = "hello";
        String str4 = "hello";
        System.out.println("(str3==str4) = " + (str3 == str4));
        System.out.println("(str3.equals(str4)) = " + (str3.equals(str4)));

        // 불변객체 + pool로 구현되었구나..
        str4 = "hi";
        System.out.println("str3 = " + str3);
        System.out.println("str4 = " + str4);
        System.out.println("변경 후 (str3==str4) = " + (str3 == str4));
        System.out.println("변경 후 (str3.equals(str4)) = " + (str3.equals(str4)));

    }
}
