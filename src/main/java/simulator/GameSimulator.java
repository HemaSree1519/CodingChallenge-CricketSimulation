package simulator;

import model.Player;
import model.State;
import model.Team;
import procedure.GameProcedure;
import rules.Rules;

import java.util.ArrayList;
import java.util.List;

public class GameSimulator {
    private GameProcedure gameProcedure;

    public GameSimulator(GameProcedure gameProcedure) {
        this.gameProcedure = gameProcedure;
    }

    public List<Player> simulateMatch(State currentState, List<Player> players, Team team, int totalBalls) {
        int totalScore = 0;
        List<Player> updatedPlayers = new ArrayList<>(players);
        System.out.println("Initial State :" + currentState);
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
            System.out.println("Intermediate :" + currentState);
            currentState = processNextState(updatedPlayers, currentState);

            if (currentState.getCurrentWicketLeft() == 0 || totalScore > team.getRunsToWin()) {
                break;
            }
        }
        System.out.println("Final : " + currentState);
        if (currentState.getCurrentRunsToWin() <= 0) {
            System.out.println("Final Result - Bengaluru won by " + currentState.getCurrentWicketLeft() + " wicket and " + (totalBalls - currentState.getCurrentBallsPlayed()) + " balls remaining");
        } else {
            System.out.println("Final Result - Bengaluru lost by" + currentState.getCurrentWicketLeft() + " wicket and " + (totalBalls - currentState.getCurrentBallsPlayed()) + " balls remaining");
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
