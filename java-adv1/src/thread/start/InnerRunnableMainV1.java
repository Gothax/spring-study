package thread.start;


import static util.MyLogger.*;

public class InnerRunnableMainV1 {


    public static void main(String[] args) {
        log("main() 시작");


        MyRunnable runnable = new MyRunnable();

        Thread thread1 = new Thread(runnable);
        thread1.start();

        Thread thread2 = new Thread(runnable);
        thread2.start();


        log("main() 종료");
    }


    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            log("run()");
        }
    }

}
