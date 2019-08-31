package exceptions;

public class InvalidTeamException extends Exception {
    private final String message;

    public InvalidTeamException() {
        super();
        this.message = "\n\033[31;1mTeam is Invalid as overs or wickets or runs to win are less than 1 or teams name might be not provided\033[0m";
    }
    @Override
    public String toString(){
        return message;
    }
}
