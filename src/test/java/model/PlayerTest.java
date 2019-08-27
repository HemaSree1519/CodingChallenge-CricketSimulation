package model;

import model.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PlayerTest {
    Player player;
    Team team;

    @Before
    public void setUp() throws Exception {
        team = new Team("Bengaluru", "Chennai", 4, 40, 4);
        player = new Player("Hema",team, Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), 0, 0, false);
    }

    @Test
    public void getNameTest() {
        String playerName = player.getName();
        assertEquals("Hema", playerName);
    }

    @Test
    public void getTeamTest(){
        Team playerTeam = player.getTeam();
        assertEquals(team,playerTeam);
    }

    @Test
    public void getProbabilityTest() {
        List<Double> playerProbablity = player.getProbability();
        assertEquals(Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), playerProbablity);
    }

    @Test
    public void getTotalRunsTest() {
        int playerTotalRuns = player.getTotalRuns();
        assertEquals(0, playerTotalRuns);

    }

    @Test
    public void getTotalBallsPlayedTest() {
        int playerTotalBallsPlayed = player.getTotalBallsPlayed();
        assertEquals(0, playerTotalBallsPlayed);
    }

    @Test
    public void getIsOutTest() {
        boolean playerIsOut = player.getIsOut();
        assertFalse(playerIsOut);
    }
}
