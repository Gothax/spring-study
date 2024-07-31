package exception.ex3.exception;

public class ConnectExceptionV3 extends NetworkClientExceptionV3 {

    private final String adress;

    public ConnectExceptionV3(String adress, String message) {
        super(message);
        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }
}
