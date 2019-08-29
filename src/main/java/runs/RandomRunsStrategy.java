package runs;

import model.Player;
import model.State;
import rules.Rules;
import utils.MatchUtils;

import java.util.List;

public abstract class RandomRunsStrategy implements RunsStrategy {
    private Rules[] rules;

    public Rules[] getRules() {
        return rules;
    }

    public void setRules(Rules[] rules) {
        this.rules = rules;
    }

    public int scoreRuns(State currentState, List<Player> players) {
        String playerName = currentState.getCurrentStriker();
        int playerPosition = MatchUtils.findPlayerPosition(playerName, players);
        double sumOfPlayerProbabilities = players.get(playerPosition).getProbability().stream()
                .mapToDouble(Double::doubleValue).sum();
        double randomNumber = MatchUtils.randomNumberInRange(0, (int) sumOfPlayerProbabilities);
        double weightSum = 0;
        List<Double> playersProbabilities = players.get(playerPosition).getProbability();
        for (int i = 0; i < playersProbabilities.size(); i++) {
            weightSum += playersProbabilities.get(i);
            if (randomNumber <= weightSum) {
                if (i == playersProbabilities.size() - 1)
                    break;
                return i;
            }
        }
        return -1;
    }
}
