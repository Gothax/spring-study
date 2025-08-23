package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class BoundedQueueV6_3 implements BoundedQueue{

    private BlockingQueue<String> queue;

    public BoundedQueueV6_3(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        boolean offer = false;
        try {
            offer = queue.offer(data, 1, TimeUnit.NANOSECONDS);
            log("저장 시도 결과 : " + offer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String take() {
        String poll = null;
        try {
            poll = queue.poll(2, TimeUnit.SECONDS);
            log("꺼내기 시도 결과 : " + poll);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return poll;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
