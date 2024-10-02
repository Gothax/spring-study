package thread.start;

public class HelloRunnableMain {


    // 실행 결과는 같다
    // 하지만 스레드와 해당 스레드가 실행할 작업이 분리되어 있다는 점이 다르다
    // 실행할 작업을 생성자로 Thread 로 전달
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main start");

        HelloRunnable runnable = new HelloRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println(Thread.currentThread().getName() + ": main end");

    }
}
