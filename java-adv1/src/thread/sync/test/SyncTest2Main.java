package thread.sync.test;

import static util.MyLogger.log;

public class SyncTest2Main {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                counter.count();
            }
        };

        Thread thread1 = new Thread(task, "thread1");
        Thread thread2 = new Thread(task, "thread2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }


    static class Counter{

        //
        public void count() {
            int localCount = 0; // 지역 변수는 스택 영역에 생성되므로 공유되지 않음
            for (int i = 0; i < 10000; i++) {
                localCount++;
            }
            log("로컬 카운트: " + localCount);
        }

    }
}
