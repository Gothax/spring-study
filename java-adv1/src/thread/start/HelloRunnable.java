package thread.start;

public class HelloRunnable implements Runnable {

    // Runnable 인터페이스를 구현하는게 더 나은 방법인 이유
    // 1. 자바는 단일 상속만 허용 -> 다른 클래스를 상속받으면 thread 상속 불가
    // 2. 유연성 부족 -> 뒤에서 나옴
    // 3. 코드 분리 -> 스레드와 실행할 작업을 분리해 코드 가독성 증가

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": run()");
    }
}
