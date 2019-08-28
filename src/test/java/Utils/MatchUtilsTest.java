package Utils;

import model.Player;
import model.State;
import model.Team;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class MatchUtilsTest {
    private List<Player> players;

    @Before
    public void setup() {
        Team team = new Team("Bengaluru", "Chennai", 4, 40, 4);
        players = new ArrayList<>();
        players.add(
                new Player("Kirat Boli", team, Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(
                new Player("NS Nodhi", team, Arrays.asList(10.0, 40.0, 20.0, 5.0, 10.0, 1.0, 4.0, 10.0), 0, 0, false));
        players.add(
                new Player("R Rumrah", team, Arrays.asList(20.0, 30.0, 15.0, 5.0, 5.0, 1.0, 4.0, 20.0), 0, 0, false));
        players.add(new Player("Shashi Henra", team, Arrays.asList(30.0, 25.0, 5.0, 0.0, 5.0, 1.0, 4.0, 30.0), 0, 0,
                false));

    }

    @Test
    public void changeStrikeWhenStrikerIsOutTest() {
        State currentState = new State("Kirat Boli", "NS Nodhi", 4, 3, 5, 25, false, 0);
        players.get(0).setOut(true);
        State nextState = MatchUtils.changeStrike(players, currentState);
        assertEquals("R Rumrah", nextState.getCurrentStriker());
    }
}
