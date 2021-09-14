package errors;

public class NotEnoughInStock extends Exception {
    public NotEnoughInStock(String message) {
        super(message);
    }
}
