package thread.voloatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {


    public static void main(String[] args) {

        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "myTaskThread");
        log("runflag = " + myTask.runFlag);
        thread.start();

        sleep(1000);
        log("runFlag를 false로 변경 시도");
        myTask.runFlag = false; // 작업 중지 요청
        log("runflag = " + myTask.runFlag);

        log("main thread 종료");

    }

    static class MyTask implements Runnable{

        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("작업 시작");
            while (runFlag) {
                System.out.println("hello");
            }
            log("작업 종료");
        }

    }

}
