package model;

import org.junit.Before;
import org.junit.Test;

public class TeamTest {
    Team team;
    @Before
    public void setup(){
        team = new Team("Bengaluru");
    }

    @Test
    public void getPlayingTeamTest(){
        String playingTeam = team.getPlayingTeam();
        assertEquals("Bengaluru", playingTeam);
    }
}
