package jpabook.jpashop.exception;

public class NoteEnoughStockException extends RuntimeException {
    public NoteEnoughStockException() {
        super();
    }

    public NoteEnoughStockException(String message) {
        super(message);
    }

    public NoteEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteEnoughStockException(Throwable cause) {
        super(cause);
    }

}
