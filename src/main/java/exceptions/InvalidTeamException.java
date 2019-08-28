package exceptions;

public class InvalidTeamException extends Exception {
    private final String message;

    public InvalidTeamException(String message) {
        super();
        this.message = message;
    }
}
