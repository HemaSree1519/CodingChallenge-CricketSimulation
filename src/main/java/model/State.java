package model;

public class State {

    private String currentStriker;
    private String currentNonStriker;
    private int currentRunCount;
    private int currentWicketLeft;
    private int currentBallsPlayed;
    private int currentRunsToWin;
    private boolean currentPlayerIsOut;
    private int currentPlayerPosition;

    public State(String currentStriker, String currentNonStriker, int currentRunCount, int currentWicketLeft,
                 int currentBallsPlayed, int currentRunsToWin, boolean currentPlayerIsOut, int currentPlayerPosition) {
        this.currentStriker = currentStriker;
        this.currentNonStriker = currentNonStriker;
        this.currentRunCount = currentRunCount;
        this.currentWicketLeft = currentWicketLeft;
        this.currentBallsPlayed = currentBallsPlayed;
        this.currentRunsToWin = currentRunsToWin;
        this.currentPlayerIsOut = currentPlayerIsOut;
        this.currentPlayerPosition = currentPlayerPosition;
    }

    public int getCurrentPlayerPosition() {
        return currentPlayerPosition;
    }

    public void setCurrentPlayerPosition(int currentPlayerPosition) {
        this.currentPlayerPosition = currentPlayerPosition;
    }

    public String getCurrentStriker() {
        return currentStriker;
    }

    public void setCurrentStriker(String currentStriker) {
        this.currentStriker = currentStriker;
    }

    public String getCurrentNonStriker() {
        return currentNonStriker;
    }

    public void setCurrentNonStriker(String currentNonStriker) {
        this.currentNonStriker = currentNonStriker;
    }

    public int getCurrentRunCount() {
        return currentRunCount;
    }

    public void setCurrentRunCount(int currentRunCount) {
        this.currentRunCount = currentRunCount;
    }

    public int getCurrentWicketLeft() {
        return currentWicketLeft;
    }

    public void setCurrentWicketLeft(int currentWicketLeft) {
        this.currentWicketLeft = currentWicketLeft;
    }

    public int getCurrentBallsPlayed() {
        return currentBallsPlayed;
    }

    public void setCurrentBallsPlayed(int currentBallsPlayed) {
        this.currentBallsPlayed = currentBallsPlayed;
    }

    public int getCurrentRunsToWin() {
        return currentRunsToWin;
    }

    public void setCurrentRunsToWin(int currentRunsToWin) {
        this.currentRunsToWin = currentRunsToWin;
    }

    public boolean isCurrentPlayerOut() {
        return currentPlayerIsOut;
    }

    public void setCurrentPlayerOut(boolean currentPlayerIsOut) {
        this.currentPlayerIsOut = currentPlayerIsOut;
    }

    public State copy() {
        return new State(currentStriker, currentNonStriker, currentRunCount, currentWicketLeft, currentBallsPlayed,
                currentRunsToWin, currentPlayerIsOut, currentPlayerPosition);
    }
}
