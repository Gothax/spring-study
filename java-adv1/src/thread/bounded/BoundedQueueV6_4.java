package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BoundedQueueV6_4 implements BoundedQueue{

    private BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        queue.add(data); // 큐가 꽉 찼을 때 IllegalStateException 발생
    }

    @Override
    public String take() {
        return queue.remove(); // 큐가 비었을 때 NoSuchElementException 발생
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
