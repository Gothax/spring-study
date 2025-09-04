package thread.executor.future;

import java.util.Random;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class RunnableMain {

    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "Thread-1");
        thread.start();
        thread.join();

        // 별도의 스레드에서 만든 값을 가져오는 것이 문제점임 - join으로 기다리고, 필드로 값을 꺼냄
        // return으로 값을 반환하는 것이 Future, Callable
        int result = task.value;
        log("result value = " + result);
    }

    static class MyRunnable implements Runnable {

        int value;

        @Override
        public void run() {
            log("Runnable 시작");

            sleep(2000);
            value = new Random().nextInt(10);
            log("create value = " + value);

            log("Runnable 완료");
        }

    }
}
