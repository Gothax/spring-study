package nested.nested.anonymous.ex;

import java.util.Random;

public class Ex1MainRefMain5 {

    public static void hello(Process process){

        System.out.println("시작");
        process.run();
        System.out.println("종료");

    }

    public static void main(String[] args) {


        hello(() -> {
            int randomValue = new Random().nextInt(6) + 1;
            System.out.println("\"주사위\" + randomValue = " + "주사위" + randomValue);
        });
        hello(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("i = " + i);
            }
        });
    }
}
