package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV3 {

    /**
     * sleep으로 다른 스레드 작업을 기다려서 해결하는 방법
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        log("Start");

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        log("join - 메인 스레드가 thread1, thread2 작업이 완료될 때까지 기다림");
        thread1.join();
        thread2.join();
        log("메인 스레드 대기 완료");


        log("task.result=" + task1.result);
        log("task2.result=" + task2.result);

        int sumAll = task1.result + task2.result;
        log("sumAll=" + sumAll);

        log("End");
    }

    static class SumTask implements Runnable {

        int startValue;
        int endValue;
        int result=0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업 시작");

            sleep(2000);
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            result = sum;

            log("작업 완료, result=" + result);
        }

    }
}
