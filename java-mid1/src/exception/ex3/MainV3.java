package exception.ex3;


import java.util.Scanner;

public class MainV3 {

    public static void main(String[] args) {
//        NetworkServiceV1_1 service = new NetworkServiceV1_1();
//        NetworkServiceV1_2 service = new NetworkServiceV1_2();
//        NetworkServiceV2_1 service = new NetworkServiceV2_1();
//        NetworkServiceV2_2 service = new NetworkServiceV2_2();
//        NetworkServiceV2_3 service = new NetworkServiceV2_3();
//        NetworkServiceV2_4 service = new NetworkServiceV2_4();
//        NetworkServiceV3_1 service = new NetworkServiceV3_1();
        NetworkServiceV3_2 service = new NetworkServiceV3_2();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("전송할 문자 : ");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            service.sendMessage(input);
            System.out.println();
        }
        System.out.println("정상 종료");
    }
}
