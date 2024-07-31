package lang.object.tostring;

public class ToStringMain1 {

    public static void main(String[] args) {
        Object obj = new Object();
        String string = obj.toString();

        System.out.println("string = " + string);
        System.out.println("obj = " + obj);
    }
}
