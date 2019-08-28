package rules;

import utils.MatchUtils;
import model.Player;
import model.State;

import java.util.List;

public class PlayerOut implements Rules {
    public State nextState(State currentState, List<Player> players) {
        State nextState = currentState.copy();
        if (currentState.isCurrentPlayerOut()) {
           nextState = MatchUtils.changeStrike(players, nextState);
        }
        return nextState;
    }
}