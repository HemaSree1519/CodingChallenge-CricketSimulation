package model;

import java.util.List;

public class Player {
    private String name;
    private Team team;
    private List<Double> probability;
    private int totalRuns;
    private int totalBallsPlayed;
    private boolean isOut;

    public Player(String name, Team team, List<Double> probability, int totalRuns, int totalBallsPlayed, boolean isOut) {
        this.name = name;
        this.team = team;
        this.probability = probability;
        this.totalRuns = totalRuns;
        this.totalBallsPlayed = totalBallsPlayed;
        this.isOut = isOut;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
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

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean isOut) {
        this.isOut = isOut;
    }
}
