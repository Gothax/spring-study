package nested.nested;

public class NestedOuter {

    private static int outClassValue = 3;
    private int outInstanceValue = 2;

    static class Nested{
        private int nestedInstanceValue = 1;

        public void print(){
            System.out.println("nestedInstanceValue = " + nestedInstanceValue);
            // 바깥 인스턴스 멤버에는 접근할 수 없다
//            System.out.println("outInstanceValue = " + outInstanceValue);
            System.out.println("outClassValue = " + outClassValue);
        }


    }
}
