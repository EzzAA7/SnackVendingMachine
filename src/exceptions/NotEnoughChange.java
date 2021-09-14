package exceptions;

public class NotEnoughChange extends Exception {
    public NotEnoughChange(String message) {
        super(message);
    }
}
