package exceptions;

public class NoPlayersException extends Exception {
    private final String message;

    public NoPlayersException(String message) {
        super();
        this.message = message;
    }
}
