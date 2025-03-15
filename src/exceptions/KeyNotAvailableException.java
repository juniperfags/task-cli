package exceptions;

public class KeyNotAvailableException extends Exception {

    public KeyNotAvailableException(String key) {
        super(key);
    }

}