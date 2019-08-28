package Utils;

import model.Team;

public class ValidationUtils {
    private ValidationUtils(){}
    public static boolean isInvalidTeam(final Team team){
        return (team.getPlayingTeam().isEmpty() || team.getOppositionTeam().isEmpty());
    }
}
