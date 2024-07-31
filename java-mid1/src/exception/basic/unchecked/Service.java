package exception.basic.unchecked;


/**
 * unchecked 예외는
 * 예외를 잡거나 던지지 않아도 된다 - 자동으로 throw
 */
public class Service {
    Client client = new Client();

    public void callCatch(){
        try {
            client.call();
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        System.out.println("정상 흐름");
    }

    public void callThrow(){
        client.call();
    }
}
