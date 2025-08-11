package thread.control;

import thread.start.HelloRunnable;

import static util.MyLogger.log;

public class ThreadInfoMain {

    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();
        log("mainThread: " + mainThread);
        log("mainThread.threadId(): " + mainThread.threadId());
        log("mainThread.threadName(): " + mainThread.getName());
        log("mainThread.threadPriority(): " + mainThread.getPriority());
        log("mainThread.group(): " + mainThread.getThreadGroup());
        log("mainThread.getState(): " + mainThread.getState());

        Thread myThread = new Thread(new HelloRunnable(), "myThread");
//        myThread.start();
        log("myThread: " + myThread);
        log("myThread.threadId(): " + myThread.threadId());
        log("myThread.threadName(): " + myThread.getName());
        log("myThread.threadPriority(): " + myThread.getPriority());
        log("myThread.group(): " + myThread.getThreadGroup());
        log("myThread.getState(): " + myThread.getState());

    }

}
