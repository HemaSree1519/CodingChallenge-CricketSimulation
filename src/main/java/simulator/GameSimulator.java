package simulator;

import exceptions.InvalidTeamException;
import exceptions.NoPlayersException;
import model.Player;
import model.State;
import model.Team;
import rules.Rules;
import runs.RandomRunsStrategy;
import runs.RunsStrategy;
import utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

public class GameSimulator {
    private RunsStrategy randomRunsStrategy;

    public GameSimulator(RunsStrategy randomRunsStrategy) {
        this.randomRunsStrategy = randomRunsStrategy;
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
            displayCommentary(currentState, team);
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

    private void displayCommentary(State currentState, Team team) {
        int ballsPlayed = currentState.getCurrentBallsPlayed();
        String overs = String.valueOf(Math.round(ballsPlayed / 6));
        String balls = String.valueOf(ballsPlayed % 6);
        if (balls.equals("0")) {
            System.out.println(((Integer.parseInt(overs) - 1) + "." + 6) + " " + currentState.getCurrentStriker() + " scores " + currentState.getCurrentRunCount() + (currentState.getCurrentRunCount() > 1 ? " runs" : " run"));
            if (currentState.getCurrentRunsToWin() > 0 && currentState.getCurrentWicketLeft() > 0) {
                int oversLeft = team.getOvers() - Integer.parseInt(overs);
                System.out.println("\n\033[1m\033[1m" + oversLeft + (oversLeft > 1 ? " overs" : " over") + " left. " + currentState.getCurrentRunsToWin() + " runs to win\033[0m\n");
            }
        } else {
            System.out.println(Double.valueOf(overs + "." + balls) + " " + currentState.getCurrentStriker() + " scores " + currentState.getCurrentRunCount() + (currentState.getCurrentRunCount() > 1 ? " runs" : " run"));
        }
    }

    private State processNextState(List<Player> players, State currentState) {
        State newState = currentState.copy();
        for (Rules rules : randomRunsStrategy.getRules()) {
            newState = rules.nextState(newState, players);
        }
        return newState;
    }
}
