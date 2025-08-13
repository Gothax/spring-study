package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

    /**
     * 스레드 작업을 중지하는 방법 - thread interrupted()
     * @param args
     */
    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "myTaskThread");
        thread.start();

        sleep(100);
        log("작업 중단 지시 thread.interrupt() 호출");
        thread.interrupt(); // 작업 중지 요청
        log("work 스레드 인터럽트 상태1= " + thread.isInterrupted());


    }

    static class MyTask implements Runnable {


        @Override
        public void run() {

            while (!Thread.interrupted()) { // interrupt 상태로 변경해줌 (interrupted true면 true를 반환하고 상태를 false로 변경함)
                log("작업중");
            }

            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
            log("state= " + Thread.currentThread().getState());


            log("자원 정리");
            log("작업 종료");
        }

    }

}
