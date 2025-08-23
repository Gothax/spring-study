package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

public class BoundedQueueV5 implements BoundedQueue{

    private final Lock lock = new ReentrantLock();
    private final Condition producerCondition = lock.newCondition();
    private final Condition consumerCondition = lock.newCondition();

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV5(int max) {
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
                producerCondition.await();
//                wait(); // RUNNABLE -> WAITING, 락 반납
                log("[put] 생산자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
            queue.offer(data);
            log("[put] 생산자 데이터 저장, consumerCondition.signal() 호출");
            // 원래는 this.notify 인데 -> 인스턴스의 wait set
            // 이건 lock의 condition wait set에서 대기 중인 스레드 하나를 깨운다
            // 생산자 스레드는 소비자 스레드를 깨운다
            consumerCondition.signal();
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
                    consumerCondition.await();
                    log("[take] 소비자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            String data = queue.poll();
            log("[take] 소비자 데이터 획득, producerCondition.signal() 호출");
//            notify(); // 대기 스레드, WAIT -> BLOCKED
            // 소비자 스레드는 생산자 스레드를 깨운다
            producerCondition.signal();
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
