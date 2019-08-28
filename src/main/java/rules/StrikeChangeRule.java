package rules;

import Utils.MatchUtils;
import model.Player;
import model.State;

import java.util.List;

public class StrikeChangeRule implements Rules {
    @Override
    public State nextState(State currentState, List<Player> players) {
        State newState = currentState.copy();
        int currentRunCount = newState.getCurrentRunCount();
        int currentBallsPlayedCount = newState.getCurrentBallsPlayed();
        if (!currentState.isCurrentPlayerOut()
                && (currentRunCount == 1 || currentRunCount == 3 || currentRunCount == 5)
                && !currentState.getCurrentNonStriker().isEmpty()) {
            newState = MatchUtils.changeStrike(players, newState);
        }
        if (!currentState.isCurrentPlayerOut() && currentBallsPlayedCount % 6 == 0
                && !currentState.getCurrentNonStriker().isEmpty()) {
            newState = MatchUtils.changeStrike(players, newState);
        }
        return newState;
    }
}
