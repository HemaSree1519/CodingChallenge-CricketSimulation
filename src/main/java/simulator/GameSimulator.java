package simulator;

import exceptions.InvalidTeamException;
import exceptions.NoPlayersException;
import model.Player;
import model.State;
import model.Team;
import procedure.GameProcedure;
import rules.Rules;
import utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

public class GameSimulator {
    private GameProcedure gameProcedure;

    public GameSimulator(GameProcedure gameProcedure) {
        this.gameProcedure = gameProcedure;
    }

    public List<Player> applyRules(List<Player> players) throws NoPlayersException, InvalidTeamException {
        if (players.isEmpty()) {
            throw new NoPlayersException("Team size is insufficient");
        }
        final Team team = players.get(0).getTeam();
        if (ValidationUtils.isInvalidTeam(team)) {
            throw new InvalidTeamException(
                    "Team is Invalid as overs or wickets or runs to win are less than 1 or teams name might be not provided");
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
        List<Player> updatedPlayers = new ArrayList<>(players);
        while (currentState.getCurrentBallsPlayed() < totalBalls) {
            int runsScored = gameProcedure.simulateRuns(currentState, players);
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
            currentState = processNextState(updatedPlayers, currentState);

            if (currentState.getCurrentWicketLeft() == 0 || totalScore > team.getRunsToWin()) {
                break;
            }
        }
        if (currentState.getCurrentRunsToWin() <= 0) {
            System.out.println("\033[1mBengaluru won by " + currentState.getCurrentWicketLeft() + " wicket and " + (totalBalls - currentState.getCurrentBallsPlayed()) + " balls remaining\033[0m");
        } else {
            System.out.println("\033[1mBengaluru lost by " + currentState.getCurrentWicketLeft() + " wicket and " + (totalBalls - currentState.getCurrentBallsPlayed()) + " balls remaining\033[0m");
        }
        return updatedPlayers;
    }

    private State processNextState(List<Player> players, State currentState) {
        State newState = currentState.copy();
        for (Rules rules : gameProcedure.getRules()) {
            newState = rules.nextState(newState, players);
        }
        return newState;
    }
}
