package network.tcp.autoclosable;

public class ResourceCloseMainV2 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("call exception 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("close exception 예외 처리");
            throw new RuntimeException(e);
        }

    }

    private static void logic() throws CallException, CloseException {

        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;

        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");
            resource1.call();
            resource2.callEx();
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        } finally { // finally 블록을 실행하면서도 예외가 발생하는 상황 + 핵심적인 call exception이 올라오지 않고 close exception이 올라오는 상황이 생김
            System.out.println("자원 정리");
            if (resource2 != null) {
                resource2.closeEx(); // resource2를 생성하기 전에 예외가 발생할 수 있으므로 null 체크 필요
            }
            if (resource1 != null) {
                resource1.closeEx();
            }
        }

    }

}
