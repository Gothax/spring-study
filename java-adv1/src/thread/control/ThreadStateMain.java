package thread.control;

import static util.MyLogger.log;

public class ThreadStateMain {

    public static void main(String[] args) throws InterruptedException {
        log("start");

        Thread thread = new Thread(new MyRunnable(), "myThread");
        log("my thread state : " + thread.getState()); // NEW
        log("my thread start");
        thread.start();

        // 메인 스레드에서 잠들어 있는 my thread 상태를 확인
        Thread.sleep(1000);
        log("my thread state after start : " + thread.getState()); // TIMED_WAITING

        log("end");
    }


    static class MyRunnable implements Runnable{

        @Override
        public void run() {
            log("start");
            log("my thread state : " + Thread.currentThread().getState()); // RUNNABLE

            log("sleep start");
            sleep5s();
            log("sleep end");

            log("my thread state : " + Thread.currentThread().getState()); // RUNNABLE
            log("end");
        }

        private void sleep5s() {
            try {
                Thread.sleep(5000);
                // 여기서 상태를 찍으면 5초 후에 RUNNABLE 상태로 찍힘
            } catch (InterruptedException e) {
                log("Thread was interrupted: " + e.getMessage());
            }
        }
    }
}
