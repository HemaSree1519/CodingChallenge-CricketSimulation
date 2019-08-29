package runs;

import model.Player;
import model.State;
import rules.Rules;

import java.util.List;

public interface RunsStrategy {
    Rules[] getRules();

    void setRules(Rules[] rules);

    int scoreRuns(State currentState, List<Player> players);
}
