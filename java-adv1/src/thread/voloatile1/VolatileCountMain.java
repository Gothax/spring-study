package thread.voloatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileCountMain {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "myTaskThread");
        thread.start();

        sleep(1000);

        myTask.flag = false; // 작업 중지 요청
        log("flag= " + myTask.flag + ", count= " + myTask.count + " in main");
    }

    static class MyTask implements Runnable {

        volatile boolean flag = true;
        volatile long count;

        @Override
        public void run() {
            while (flag) {
                count++;
                if (count % 100_000_000 == 0) {
                    log("flag= " + flag + ", count= " + count + " in while");
                }
            }
            log("작업 종료, flag= " + flag + ", count= " + count);
        }

    }

}
