package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV1 {

    public static void main(String[] args) {
        Thread parkThread = new Thread(new ParkTest(), "thread-1");
        parkThread.start();

        sleep(100);
        log("thread-1 state: " + parkThread.getState());

        log("main->thread1 unpark thread-1");
//        LockSupport.unpark(parkThread); // 1, unpark 호출 - 이때는 interrupt = false로 찍힘
        parkThread.interrupt(); //2. 인터럽트 호출

    }

    static class ParkTest implements Runnable {
        @Override
        public void run() {
            log("park 시작");
            LockSupport.park();
            log("park 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());

        }
    }
}
