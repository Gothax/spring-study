package thread.start;


import static util.MyLogger.log;

public class InnerRunnableMainV2 {


    public static void main(String[] args) {
        log("main() 시작");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log("run()");
            }
        };

        Thread thread1 = new Thread(runnable);
        thread1.start();

        Thread thread2 = new Thread(runnable);
        thread2.start();


        log("main() 종료");
    }


}
