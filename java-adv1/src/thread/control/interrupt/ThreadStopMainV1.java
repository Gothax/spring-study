package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV1 {

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
        log("작업 중단 지시 runflag false로 변경");
        task.runFlag = false; // 작업 중지 요청

    }

    static class MyTask implements Runnable {

        volatile boolean runFlag = true;

        @Override
        public void run() {
            while (runFlag) {
                log("작업 중");
                sleep(3000);
            }
            log("자원 정리");
            log("작업 종료");
        }

    }

}
