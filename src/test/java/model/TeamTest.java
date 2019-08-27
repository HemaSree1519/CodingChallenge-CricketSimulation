package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class TeamTest {
    Team team;

    @Before
    public void setup(){
        team = new Team("Bengaluru", "Chennai", 4, 40, 4);
    }

    @Test
    public void getPlayingTeamTest(){
        String playingTeam = team.getPlayingTeam();
        assertEquals("Bengaluru", playingTeam);
    }
    @Test
    public void getOppositionTeamTest(){
        String oppositionTeam = team.getOppositionTeam();
        assertEquals("Chennai", oppositionTeam);
    }
    @Test
    public void getOversTest(){
        int overs = team.getOvers();
        assertEquals(4, overs);
    }
    @Test
    public void getWicketsTest(){
        int wickets = team.getWickets();
        assertEquals(40, wickets);
    }
    @Test
    public void getRunsToWinTest(){
        int runsToWin = team.getRunsToWin();
        assertEquals(4, runsToWin);
    }
}
