package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {

    /**
     * 스레드 작업을 중지하는 방법 - thread interrupt()
     * my task 스레드에서 interrupt 상태를 변경하지 않기 때문애 자원을 정리하다가 interrupt ture를 보고 자원을 정리하지 못하는 문제가 발생할 수 있음
     * 즉 interrupt의 목적을 달성했다면 정상으로 돌려야한다
     * @param args
     */
    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "myTaskThread");
        thread.start();

        sleep(1000);
        log("작업 중단 지시 thread.interrupt() 호출");
        thread.interrupt(); // 작업 중지 요청
        log("work 스레드 인터럽트 상태1= " + thread.isInterrupted());


    }

    static class MyTask implements Runnable {


        @Override
        public void run() {

            while (!Thread.currentThread().isInterrupted()) {
                log("작업중");
            }

            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
            log("state= " + Thread.currentThread().getState());


            log("자원 정리");
            log("작업 종료");
        }

    }

}
