package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {

    /**
     * 스레드 작업을 중지하는 방법 - 변수
     * 스레드가 즉각 중지되지 않는 문제점이 발생함
     * @param args
     */
    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "myTaskThread");
        thread.start();

        sleep(4000); // 4초 후에 작업 중지 요청
        log("작업 중단 지시 thread.interrupt() 호출");
        thread.interrupt(); // 작업 중지 요청
        log("work 스레드 인터럽트 상태1= " + thread.isInterrupted());


    }

    static class MyTask implements Runnable {


        @Override
        public void run() {

            try {

                while (true) {
                    log("작업 중");
                    Thread.sleep(3000);
                }

            } catch (InterruptedException e) {
                log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
                log("interrupt message = " + e.getMessage());
                log("state= " + Thread.currentThread().getState());
            }


            log("자원 정리");
            log("작업 종료");
        }

    }

}
