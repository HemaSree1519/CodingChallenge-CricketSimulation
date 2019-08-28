package Utils;

import model.Team;

public class ValidationUtils {
    private ValidationUtils(){}
    public static boolean isValidTeam(final Team team){
        return (team.getPlayingTeam().isEmpty() || team.getOppositionTeam().isEmpty());
    }
}
