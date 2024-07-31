package nested.nested.local;

public class LocalouterV2 {

    private int outInstanceVar = 3;

    public void process(int paramVar) {
        int localVar = 1;

        class LocalPrinter implements Printer{
            int value = 0;


            @Override
            public void print() {
                System.out.println("value = " + value);
                System.out.println("localVar = " + localVar);
                System.out.println("paramVar = " + paramVar);
                System.out.println("outInstanceVar = " + outInstanceVar);

            }
        }

        LocalPrinter localPrinter = new LocalPrinter();
        localPrinter.print();

    }

    public static void main(String[] args) {
        LocalouterV2 localouterV1 = new LocalouterV2();
        localouterV1.process(10);
    }
}
