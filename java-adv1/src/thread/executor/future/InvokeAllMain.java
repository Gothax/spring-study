package thread.executor.future;

import thread.executor.CallableTask;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static util.MyLogger.log;

public class InvokeAllMain {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);

        CallableTask taskA = new CallableTask("task1", 1000);
        CallableTask taskB = new CallableTask("task2", 2000);
        CallableTask taskC = new CallableTask("task3", 3000);

        List<CallableTask> tasks = List.of(taskA, taskB, taskC);

        List<Future<Integer>> futures = es.invokeAll(tasks);
        for (Future<Integer> future : futures) {
            try {
                Integer result = future.get();
                log("valUE = " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        es.close();
    }
}
