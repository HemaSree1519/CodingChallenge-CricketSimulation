package utils;

import model.Player;
import model.State;

import java.util.List;

public class MatchUtils {
    private MatchUtils() {
    }

    public static State changeStrike(List<Player> players, State currentState) {

        State nextState = currentState.copy();
        String striker = currentState.getCurrentStriker();
        String nonStriker = currentState.getCurrentNonStriker();
        int positionOfStriker = findPlayerPosition(striker, players);
        int positionOfNonStriker = findPlayerPosition(nonStriker, players);

        if (players.get(positionOfStriker).isOut()) {
            for (int i = 0; i < players.size(); i++) {
                if (!players.get(i).isOut() && !players.get(i).getName().equals(nonStriker)) {
                    nextState.setCurrentStriker(players.get(i).getName());
                    nextState.setCurrentPlayerPosition(i);
                    nextState.setCurrentPlayerOut(false);
                    return nextState;
                }
            }
            nextState.setCurrentStriker(nonStriker);
            nextState.setCurrentPlayerPosition(positionOfNonStriker);
            nextState.setCurrentPlayerOut(false);
            nextState.setCurrentNonStriker("");
            return nextState;

        } else if (players.get(positionOfNonStriker).isOut()) {
            for (int i = 0; i < players.size(); i++) {
                if (!players.get(i).isOut() && !players.get(i).getName().equals(striker)) {
                    nextState.setCurrentNonStriker(players.get(i).getName());
                    nextState.setCurrentPlayerPosition(i);
                    nextState.setCurrentPlayerOut(false);
                    return nextState;
                }
            }
        } else {
            nextState.setCurrentStriker(nonStriker);
            nextState.setCurrentPlayerPosition(positionOfNonStriker);
            nextState.setCurrentNonStriker(striker);
        }
        return nextState;
    }

    private static int findPlayerPosition(String playerName, List<Player> players) {
        players.forEach(player -> player.getName().equals(playerName));
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(playerName))
                return i;
        }
        return -1;
    }
}
