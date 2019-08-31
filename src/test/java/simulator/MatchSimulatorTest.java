package simulator;

import exceptions.InvalidTeamException;
import exceptions.NoPlayersException;
import model.Player;
import model.State;
import model.Team;
import org.junit.Before;
import org.junit.Test;
import rules.PlayerOut;
import rules.Rules;
import rules.StrikeChange;
import runs.DefaultRunsStrategy;
import runs.RunsStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchSimulatorTest {
    private List<Player> players;
    private Team team;
    private State currentState;
    private MatchSimulator matchSimulator;

    @Before
    public void setup() {
        team = new Team("Bengaluru", "Chennai", 0, 40, 4);
        players = new ArrayList<>();
        currentState = new State("Kirat Boli", "NS Nodhi", 4, 3, 5, 25, true, 0);
        RunsStrategy randomRunsStrategy = new DefaultRunsStrategy();
        matchSimulator = new MatchSimulator(randomRunsStrategy);
        Rules[] rules = new Rules[]{new PlayerOut(), new StrikeChange()};
        randomRunsStrategy.setRules(rules);
    }

    @Test(expected = NoPlayersException.class)
    public void applyRulesShouldThrowNoPlayerExceptionWhenNoPlayersTest() throws NoPlayersException, InvalidTeamException {
        matchSimulator.applyRules(players);
    }

    @Test(expected = InvalidTeamException.class)
    public void applyRulesShouldThrowInvalidTeamExceptionTest() throws NoPlayersException, InvalidTeamException {
        players.add(
                new Player("Kirat Boli", team, Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        matchSimulator.applyRules(players);
    }
}
