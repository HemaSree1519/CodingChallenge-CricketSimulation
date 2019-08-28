package rules;

import model.Player;
import model.State;
import model.Team;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StrikeChangeTest {
    private List<Player> players;
    private State currentState;
    private Rules rules;
    private State nextState;

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
        rules = new StrikeChange();
    }

    @Test
    public void strikeChangeRuleWithOverCompleteTest() {
        currentState = new State("Kirat Boli", "NS Nodhi", 2, 4, 6, 25, false, 0);
        nextState = rules.nextState(currentState, players);
        assertEquals("NS Nodhi", nextState.getCurrentStriker());
    }

    @Test
    public void strikeChangeRuleWithOddRunsTest() {
        currentState = new State("Kirat Boli", "NS Nodhi", 1, 4, 5, 25, false, 0);
        nextState = rules.nextState(currentState, players);
        assertEquals("NS Nodhi", nextState.getCurrentStriker());
    }

    @Test
    public void strikeChangeRuleWithOddRunsAndOversCompleteTest() {
        currentState = new State("Kirat Boli", "NS Nodhi", 1, 4, 6, 25, false, 1);
        nextState = rules.nextState(currentState, players);
        assertEquals("Kirat Boli", nextState.getCurrentStriker());
    }
}
