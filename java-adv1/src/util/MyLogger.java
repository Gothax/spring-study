package util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// util 패키지는 프로젝트 전반에 사용하는 코드를 의미!

public abstract class MyLogger {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void log(Object object) {
        String time = LocalTime.now().format(formatter);
        System.out.printf("%s [%9s] %s\n", time, Thread.currentThread().getName(), object);
    }
}
