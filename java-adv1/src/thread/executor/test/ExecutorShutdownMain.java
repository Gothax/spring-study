package thread.executor.test;

import thread.executor.ExecutorUtils;
import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class ExecutorShutdownMain {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("long task", 100_000));

        ExecutorUtils.printState(es);
        log("== shutdown 시작 ==");
        shutdownAndAwaitTermination(es);
        log("== shutdown 완료 ==");
        ExecutorUtils.printState(es);

    }

    private static void shutdownAndAwaitTermination(ExecutorService es) {
        es.shutdown(); // non blocking - 새로운 작업을 받지 않음, 처리 중이거나 큐에 있는 작업은 수행
        try {
            // 이미 대기중인 작업을 모두 완료할때 까지 10초 기다림
            if (!es.awaitTermination(10, TimeUnit.SECONDS)) { // 이건 blocking
                 // 정상 종료가 너무 오래걸리면
                log("서비스 정상 종료 실패 -> 강제 종료 시도");
                es.shutdownNow(); // 강제 종료 - 대기중인 작업을 취소하고, 실행중인 작업에 인터럽트 발생
                if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("서비스 강제 종료 되지 않았습니다");
                }
            }
        } catch (InterruptedException e) {
            es.shutdownNow(); // awaitTermination으로 대기중인 현재 스레드가 인터럽
        }
    }
}
