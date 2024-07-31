package exception.basic.unchecked;

/**
 * runtimeerror 상속받았으니 unchheck exception
 */
public class MyUncheckedException extends RuntimeException {

    public MyUncheckedException(String message) {
        super(message);
    }
}
