package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV2 {

    public static void main(String[] args) {
        Thread parkThread = new Thread(new ParkTest(), "thread-1");
        parkThread.start();

        sleep(100);
        log("thread-1 state: " + parkThread.getState());

    }

    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");
            LockSupport.parkNanos(2000_000_000); // 2초 동안 대기
            log("park 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());

        }

    }
}
