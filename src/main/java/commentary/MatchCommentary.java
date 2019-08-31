package commentary;

import model.State;
import model.Team;

public class MatchCommentary {
    public void display(State currentState, Team team) {
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
}
