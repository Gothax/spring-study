package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureCancelMain {

    private static boolean mayInterruptIfRunning = true;

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new MyTask());
        log("future.state : " + future.state());

        // 일정 시간 후 취소 시도
        sleep(3000);

        log("future.cancel(" + mayInterruptIfRunning + ") 호출");
        boolean cancelResult = future.cancel(mayInterruptIfRunning);
        log("cancelResult=" + cancelResult);

        try {
            log("Future result = " + future.get());
        } catch (CancellationException) {

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    static class MyTask implements Callable<String> {
        @Override
        public String call() {
            try {
                for (int i = 0; i < 10; i++) {
                    log("작업 중: i=" + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                log("인터럽트 발생");
                return "Interrupted";
            }
            return "Completed";
        }
    }

}
