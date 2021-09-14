package exceptions;

public class NotEnoughInStock extends Exception {
    public NotEnoughInStock(String message) {
        super(message);
    }
}
