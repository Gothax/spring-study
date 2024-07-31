package nested.nested.anonymous.ex;

import nested.nested.local.Printer;

import java.util.Random;

public class Ex1RefMain {

    public void process(String message){

        Printer printer = new Printer() {
            @Override
            public void print() {
                System.out.println("프로그램 시작");
                if (message.equals("Hello Dice")){
                    int randomValue = new Random().nextInt(6) + 1;
                    System.out.println("\"주사위\" + randomValue = " + "주사위" + randomValue);
                } else if (message.equals("Hello Sum")) {
                    for (int i = 0; i < 3; i++) {
                        System.out.println("i = " + i);
                    }                }
                System.out.println("프로그램 종료");
            }
        };
        printer.print();
    }



    public static void main(String[] args) {
        Ex1RefMain main = new Ex1RefMain();
        main.process("Hello Dice");
        main.process("Hello Sum");
    }
}
