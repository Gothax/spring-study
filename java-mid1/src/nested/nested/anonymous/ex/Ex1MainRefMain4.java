package nested.nested.anonymous.ex;

import java.util.Random;

public class Ex1MainRefMain4 {

    public static void hello(Process process){

        System.out.println("시작");
        process.run();
        System.out.println("종료");

    }

    public static void main(String[] args) {


        hello(new Process() {

            @Override
            public void run() {

                int randomValue = new Random().nextInt(6) + 1;
                System.out.println("\"주사위\" + randomValue = " + "주사위" + randomValue);

            }
        });
        hello(new Process() {

            @Override
            public void run() {

                for (int i = 0; i < 3; i++) {
                    System.out.println("i = " + i);
                }
            }
        });
    }
}
