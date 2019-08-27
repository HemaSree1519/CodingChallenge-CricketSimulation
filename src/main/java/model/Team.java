package model;

public class Team {
    private String playingTeam;
    private String oppositionTeam;
    private int overs;
    private int wickets;
    private int runsToWin;

    public Team(String playingTeam,String oppositionTeam, int overs, int wickets, int runsToWin) {
        this.playingTeam = playingTeam;
        this.oppositionTeam = oppositionTeam;
        this.wickets = wickets;
        this.runsToWin = runsToWin;
        this.overs = overs;
    }

    public int getOvers() {
        return overs;
    }

    public String getPlayingTeam() {
        return playingTeam;
    }

    public String getOppositionTeam() {
        return oppositionTeam;
    }

    public int getWickets() {
        return wickets;
    }

    public int getRunsToWin() {
        return runsToWin;
    }
}
