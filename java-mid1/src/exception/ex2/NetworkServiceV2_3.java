package exception.ex2;



public class NetworkServiceV2_3 {

    public void sendMessage(String data) {
        String address = "http://example.com";
        NetworkClientV2 client = new NetworkClientV2(address);
        client.initError(data);

        try {
            client.connect();
            client.send(data);
            client.disconnect();

        } catch (NetworkClientExceptionV2 e) {
            System.out.println("e.getErrorCode() = " + e.getErrorCode() + "메시지 : " + e.getMessage());
        }
    }
}
