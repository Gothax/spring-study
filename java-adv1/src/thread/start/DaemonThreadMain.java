package thread.start;

public class DaemonThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main start");

        // daemon end는 출력되지 않고 main이 종료되며 종료됨
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true); // 데몬 스레드 여부 - 기본은 사용자 스레드
        daemonThread.start();

        System.out.println(Thread.currentThread().getName() + ": main end");
    }


    // thread 만들때 - Thread 클래스를 상속받거나
    // Runnable 인터페이스를 구현하는 방법 --> 이걸 실무에서 주로 사용
    static class DaemonThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": run()");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + ": run end");
        }
    }
}
