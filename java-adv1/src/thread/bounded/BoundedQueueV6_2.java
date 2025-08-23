package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static util.MyLogger.log;

public class BoundedQueueV6_2 implements BoundedQueue{

    private BlockingQueue<String> queue;

    public BoundedQueueV6_2(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        boolean offer = queue.offer(data);
        log("저장 시도 결과 : " + offer);
    }

    @Override
    public String take() {
        String poll = queue.poll();
        log("꺼내기 시도 결과 : " + poll);
        return poll;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
