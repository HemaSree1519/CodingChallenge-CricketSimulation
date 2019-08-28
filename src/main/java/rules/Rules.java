package rules;

import model.Player;
import model.State;

import java.util.List;

public interface Rules {
    State nextState(State currentState, List<Player> players);
}
