import java.util.List;

public class Player {
    private String name;
    private List<Double> probability;
    private int totalRuns;
    private int totalBallsPlayed;
    private boolean isOut;

    public Player(String name, List<Double> probability, int totalRuns, int totalBallsPlayed, boolean isOut) {
        this.name=name;
        this.probability=probability;
        this.totalRuns=totalRuns;
        this.totalBallsPlayed=totalBallsPlayed;
        this.isOut=isOut;
    }

    public String getName() {
        return name;
    }

    public List<Double> getPropability() {
        return probability;
    }

    public int getTotalRuns() {
    return totalRuns;
    }

    public int getTotalBallsPlayed() {
    return totalBallsPlayed;
    }

    public boolean getIsOut() {
    return isOut;
    }
}
