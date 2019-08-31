package exceptions;

public class NoPlayersException extends Exception {
    private final String message;

    public NoPlayersException() {
        super();
        this.message = "\n\033[31;1mTeam size is insufficient\033[0m";
    }

    @Override
    public String toString() {
        return message;
    }
}
