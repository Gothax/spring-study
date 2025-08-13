package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class MyPrinterV1 {

    /**
     * 변수를 사용하는 방법
     * interrupt 하기 위해 (printer thread를 종료하기 위해)
     * while문을 빠져 나오기까지 기다려야함
     * @param args
     */
    public static void main(String[] args) {

        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "PrinterThread");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            log("프린트할 문서를 입력하세요. 종료 q");
            String input = userInput.nextLine();
            if (input.equals("q")) {
                printer.work = false; // 프린터 작업 중지
                break;
            }
            printer.addJob(input);
        }

    }


    static class Printer implements Runnable {

        volatile boolean work = true;
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            while (work) {
                if (jobQueue.isEmpty()) {
                    continue;
                }

                String job = jobQueue.poll();
                log("출력 시작 : " + job + ", 대기 문서 : " + jobQueue);
                sleep(3000);
                log("출력 완료");

            }

            log("프린터 종료");
        }

        public void addJob(String job) {
            jobQueue.offer(job);
        }

    }


}
