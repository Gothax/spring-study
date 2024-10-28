package thread.start;

import java.util.Arrays;

public class HelloThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
        helloThread.start();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");


        System.out.println(Thread.currentThread().getName() + ": main() end");


        String s = "qeq\n;12";
        String[] split = s.split("\\n");
        System.out.println(Arrays.toString(split));




    }
}
