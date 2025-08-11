package thread.start;


import static util.MyLogger.log;

public class InnerRunnableMainV3 {


    public static void main(String[] args) {
        log("main() 시작");


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log("run()");
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> log("run()"));
        thread2.start();


        log("main() 종료");
    }


}
