package thread.executor.poolsize;

import thread.executor.ExecutorUtils;
import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV1 {
    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ExecutorService es = new ThreadPoolExecutor(2, 4, 3000, TimeUnit.MILLISECONDS, workQueue);
        ExecutorUtils.printState(es);

        es.execute(new RunnableTask("task1"));
        ExecutorUtils.printState(es, "task1");

        es.execute(new RunnableTask("task2"));
        ExecutorUtils.printState(es, "task2");

        es.execute(new RunnableTask("task3"));
        ExecutorUtils.printState(es, "task3");

        es.execute(new RunnableTask("task4"));
        ExecutorUtils.printState(es, "task4");

        es.execute(new RunnableTask("task5"));
        ExecutorUtils.printState(es, "task5");

        es.execute(new RunnableTask("task6"));
        ExecutorUtils.printState(es, "task6");


        try {
            es.execute(new RunnableTask("task7"));
        } catch (RejectedExecutionException e) {
            log("task7 - 작업 거부됨");
        }

        sleep(3000);
        log("== 작업 수행 완료==");
        ExecutorUtils.printState(es);

        sleep(3000);
        log("== maximum poolsize 초과 대 시간 경과 후 ==");
        ExecutorUtils.printState(es);

        es.close();
        log(" == shutdown 완료 == ");
        ExecutorUtils.printState(es);

    }
}
