package thread.collection.simple.list;

import static util.MyLogger.log;

public class SimpleListMainV2 {

    public static void main(String[] args) throws InterruptedException {
//        test(new BasicList());
//        test(new SyncList());

        // basic list는 동기화가 안됨
        BasicList basicList = new BasicList();
        // 이를 동기화하는 프록시 객체 생성
        SyncProxyList syncProxyList = new SyncProxyList(basicList);
        test(syncProxyList);
    }

    private static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        Runnable runnableA = new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("list.add(A)");
            }
        };

        Runnable runnableB = new Runnable() {
            @Override
            public void run() {
                list.add("B");
                log("list.add(B)");
            }
        };


        Thread thread1 = new Thread(runnableA);
        Thread thread2 = new Thread(runnableB);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        log(list);

    }
}
