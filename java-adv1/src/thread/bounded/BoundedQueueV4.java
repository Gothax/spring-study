package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

public class BoundedQueueV4 implements BoundedQueue{

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV4(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {

        lock.lock();
        try {
            while (queue.size() == max) {
            log("[put] 큐가 가득 참, 생산자 대기 ");
            try {
                // 원래는 this.wait 인데 -> 인스턴스의 wait set
                // 이건 lock의 condition wait set에서 대
                condition.await();
//                wait(); // RUNNABLE -> WAITING, 락 반납
                log("[put] 생산자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
            queue.offer(data);
            log("[put] 생산자 데이터 저장, condition.signal() 호출");
            // 원래는 this.notify 인데 -> 인스턴스의 wait set
            // 이건 lock의 condition wait set에서 대기 중인 스레드 하나를 깨운다
            condition.signal();
//            notify(); // 대기 스레드, WAIT -> BLOCKED

        } finally {
            lock.unlock();
        }

    }

    @Override
    public String take() {

        lock.lock();

        try {

            while (queue.isEmpty()) {
                log("[take] 큐가 비어 있음, 소비자 대기 ");
                try {
//                    wait();
                    condition.await();
                    log("[take] 소비자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            String data = queue.poll();
            log("[take] 소비자 데이터 획득, condition.signal() 호출");
//            notify(); // 대기 스레드, WAIT -> BLOCKED
            condition.signal();
            return data;

        } finally {
            lock.unlock();
        }

    }

    @Override
    public String toString() {
        return queue.toString();
    }

}
