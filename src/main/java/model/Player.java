package model;

import java.util.List;

public class Player {
    private String name;
    private List<Double> probability;
    private int totalRuns;
    private int totalBallsPlayed;
    private boolean isOut;

    public Player(String name, List<Double> probability, int totalRuns, int totalBallsPlayed, boolean isOut) {
        this.name = name;
        this.probability = probability;
        this.totalRuns = totalRuns;
        this.totalBallsPlayed = totalBallsPlayed;
        this.isOut = isOut;
    }

    public String getName() {
        return name;
    }

    public List<Double> getProbability() {
        return probability;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public int getTotalBallsPlayed() {
        return totalBallsPlayed;
    }

    public void setTotalBallsPlayed(int totalBallsPlayed) {
        this.totalBallsPlayed = totalBallsPlayed;
    }

    public void setOut(boolean isOut) {
        this.isOut = isOut;
    }

    public boolean getIsOut() {
        return isOut;
    }
}
