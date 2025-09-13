package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureExceptionMain {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(1);
        log("작업 전달");

        ExCallable task = new ExCallable();
        Future<Integer> future = es.submit(task);
        sleep(1000);


        try {
            log("future.get() 호출 시도, future.state: " + future.state() );
            log("result value= " + future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            // 실행중에 예외가 발생했을 때 터지는 예외
            log("e= " + e);
            Throwable cause = e.getCause();
            log("cause = " + cause);
        }

        es.close();


    }


    static class ExCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            log("Callable 실행, 예외 발생");
            throw new IllegalStateException("예외 발생");
        }
    }


}
