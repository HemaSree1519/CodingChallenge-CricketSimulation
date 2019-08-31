package simulator;

import commentary.MatchCommentary;
import exceptions.InvalidTeamException;
import exceptions.NoPlayersException;
import model.Player;
import model.State;
import model.Team;
import rules.Rules;
import runs.RunsStrategy;
import utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

public class MatchSimulator {
    private RunsStrategy randomRunsStrategy;

    public MatchSimulator(RunsStrategy randomRunsStrategy) {
        this.randomRunsStrategy = randomRunsStrategy;
    }

    public List<Player> applyRules(List<Player> players) throws NoPlayersException, InvalidTeamException {
        if (players.isEmpty()) {
            throw new NoPlayersException();
        }
        final Team team = players.get(0).getTeam();
        if (ValidationUtils.isInvalidTeam(team)) {
            throw new InvalidTeamException();
        }
        final int totalBalls = players.get(0).getTeam().getOvers() * 6;
        final Player firstPlayer = players.get(0) != null ? players.get(0) : new Player("");
        final Player secondPlayer = players.size() > 1 ? players.get(1) : new Player("");
        State currentState = new State(firstPlayer.getName(), secondPlayer.getName(), 0, team.getWickets(),
                0, team.getRunsToWin(), false, 0);

        return simulateMatch(currentState, players, team, totalBalls);
    }

    private List<Player> simulateMatch(State currentState, List<Player> players, Team team, int totalBalls) {
        int totalScore = 0;
        MatchCommentary commentary = new MatchCommentary();
        List<Player> updatedPlayers = new ArrayList<>(players);
        System.out.println("\033[34;1mCricket match commentary\033[0m");
        System.out.println("\n\033[1m\033[1m" + team.getOvers() + " overs left. " + currentState.getCurrentRunsToWin() + " runs to win\033[0m\n");
        while (currentState.getCurrentBallsPlayed() < totalBalls && currentState.getCurrentRunCount() < team.getRunsToWin()) {
            int runsScored = randomRunsStrategy.scoreRuns(currentState, players);
            int currentPlayerPosition = currentState.getCurrentPlayerPosition();
            Player currentPlayer = updatedPlayers.get(currentPlayerPosition);
            if (runsScored == -1) {
                currentState.setCurrentPlayerOut(true);
                currentState.setCurrentWicketLeft(currentState.getCurrentWicketLeft() - 1);
                updatedPlayers.get(currentState.getCurrentPlayerPosition()).setOut(currentState.isCurrentPlayerOut());
            } else {
                totalScore += runsScored;
                currentPlayer.setTotalRuns(currentPlayer.getTotalRuns() + runsScored);
                currentState.setCurrentRunCount(runsScored);
                currentState.setCurrentRunsToWin(currentState.getCurrentRunsToWin() - runsScored);
            }
            currentPlayer.setTotalBallsPlayed(currentPlayer.getTotalBallsPlayed() + 1);
            currentState.setCurrentBallsPlayed(currentState.getCurrentBallsPlayed() + 1);
            commentary.display(currentState, team);
            currentState = processNextState(updatedPlayers, currentState);

            if (currentState.getCurrentWicketLeft() == 0 || totalScore > team.getRunsToWin()) {
                break;
            }
        }
        if (currentState.getCurrentRunsToWin() <= 0) {
            System.out.println("\n\033[1mBengaluru won by " + currentState.getCurrentWicketLeft() + " wicket and " + (totalBalls - currentState.getCurrentBallsPlayed()) + " balls remaining\033[0m");
        } else {
            System.out.println("\n\033[1mBengaluru lost by " + currentState.getCurrentWicketLeft() + " wicket and " + (totalBalls - currentState.getCurrentBallsPlayed()) + " balls remaining\033[0m");
        }
        return updatedPlayers;
    }

    private State processNextState(List<Player> players, State currentState) {
        State newState = currentState.copy();
        for (Rules rules : randomRunsStrategy.getRules()) {
            newState = rules.nextState(newState, players);
        }
        return newState;
    }
}
