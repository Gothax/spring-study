package nested.nested.inner;

public class InnerOuter {

    private static int outClassValue = 3;
    private int outtInstanceValue = 2;

    class Inner {
        private int innerInstanceValue = 1;

        public void print(){
            // 자기 자신 접근
            System.out.println("innerInstanceValue = " + innerInstanceValue);

            // 외부 클래스 인스턴스 멤버 접근 가능, private도 가능
            System.out.println("outtInstanceValue = " + outtInstanceValue);

            // 외부 클래스 멤버 접근 가능, private도 가능
            System.out.println("outClassValue = " + outClassValue);
        }
    }
}
