package utils;

import model.Team;

public class ValidationUtils {
    private ValidationUtils() {
    }

    public static boolean isInvalidTeam(final Team team) {
        return (team.getOvers() < 1 || team.getWickets() < 1 || team.getRunsToWin() < 1
                || team.getPlayingTeam().isEmpty() || team.getOppositionTeam().isEmpty());
    }
}
