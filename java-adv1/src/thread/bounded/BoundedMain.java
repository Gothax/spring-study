package thread.bounded;

import java.util.ArrayList;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedMain {

    public static void main(String[] args) {

//        BoundedQueue queue = new BoundedQueueV1(2);
//        BoundedQueue queue = new BoundedQueueV2(2);
//        BoundedQueue queue = new BoundedQueueV3(2);
//        BoundedQueue queue = new BoundedQueueV4(2);
//        BoundedQueue queue = new BoundedQueueV5(2);
//        BoundedQueue queue = new BoundedQueueV6_1(2);
//        BoundedQueue queue = new BoundedQueueV6_2(2);
//        BoundedQueue queue = new BoundedQueueV6_3(2);
        BoundedQueue queue = new BoundedQueueV6_4(2);

        // 예제에서는 통일감을 위해 새로운 클래스를 만들지만
        // 실무에서는 이렇게 바로 자료형을 사용한다
//        BlockingQueue queue = new ArrayBlockingQueue<>(2);

        producerFirst(queue); // 생산자 먼저 실행
//        consumerFirst(queue); // 소비자 먼저 실행

    }

    private static void consumerFirst(BoundedQueue queue) {
        log("== [소비자 먼저 실행] ==, " + queue.getClass().getSimpleName() + " ==");
        ArrayList<Thread> threads = new ArrayList<>();


        startConsumer(queue, threads);
        printAllState(queue, threads);
        startProducer(queue, threads);
        printAllState(queue, threads);

        log("== [소비자 먼저 실행] 종료 ==, " + queue.getClass().getSimpleName() + " ==");
    }

    private static void producerFirst(BoundedQueue queue) {
        log("== [생산자 먼저 실행] ==, " + queue.getClass().getSimpleName() + " ==");
        ArrayList<Thread> threads = new ArrayList<>();

        startProducer(queue, threads);
        printAllState(queue, threads);
        startConsumer(queue, threads);
        printAllState(queue, threads);

        log("== [생산자 먼저 실행] 종료 ==, " + queue.getClass().getSimpleName() + " ==");
    }

    private static void startConsumer(BoundedQueue queue, ArrayList<Thread> threads) {
        System.out.println();

        log("소비자 시작");
        for (int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask(queue), "consumer" + i);
            threads.add(consumer);
            consumer.start();
            sleep(100);
        }
    }

    private static void printAllState(BoundedQueue queue, ArrayList<Thread> threads) {
        System.out.println();
        log("현재 상태 출력, 큐 데이터: " + queue);
        for (Thread thread : threads) {
            log(thread.getName() + ": " + thread.getState());
        }
    }

    private static void startProducer(BoundedQueue queue, ArrayList<Thread> threads) {
        System.out.println();

        log("생산자 시작");
        for (int i = 1; i <= 3; i++) {

            Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i);

            threads.add(producer);
            producer.start();
            sleep(100);

        }

    }


}
