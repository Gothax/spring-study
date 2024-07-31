package lang.wrapper;

public class WrapperVsPrimitve {

    public static void main(String[] args) {
        int interations = 1_000_000_000;
        long startTime, endTime;

        // sumPrimitive = 499999999500000000
        // 기본 자료형 long 시간(endTime-startTime) = 194
        long sumPrimitive = 0;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < interations; i++) {
            sumPrimitive += i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("sumPrimitive = " + sumPrimitive);
        System.out.println("기본 자료형 long 시간(endTime-startTime) = " + (endTime - startTime));

        // 래퍼 클래스 Long
//        sumWrapper = 499999999500000000
//        기본 자료형 long 시간(endTime-startTime) = 1994
        Long sumWrapper = 0L;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < interations; i++) {
            sumWrapper += i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("sumWrapper = " + sumWrapper);
        System.out.println("기본 자료형 long 시간(endTime-startTime) = " + (endTime - startTime));

    }
}
