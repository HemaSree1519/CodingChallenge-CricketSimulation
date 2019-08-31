import exceptions.InvalidTeamException;
import exceptions.NoPlayersException;
import model.Player;
import model.Team;
import runs.DefaultRunsStrategy;
import rules.PlayerOut;
import rules.Rules;
import rules.StrikeChange;
import runs.RunsStrategy;
import simulator.GameSimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CricketSimulationMain {
    public static void main(String[] args) {
        Team team;
        List<Player> players = new ArrayList<>();
        RunsStrategy randomRunsStrategy = new DefaultRunsStrategy();
        GameSimulator gameSimulator = new GameSimulator(randomRunsStrategy);
        Rules[] rules;

        team = new Team("Bengaluru", "Chennai", 4, 3, 40);
        players.add(
                new Player("Kirat Boli", team, Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(
                new Player("NS Nodhi", team, Arrays.asList(10.0, 40.0, 20.0, 5.0, 10.0, 1.0, 4.0, 10.0), 0, 0, false));
        players.add(
                new Player("R Rumrah", team, Arrays.asList(20.0, 30.0, 15.0, 5.0, 5.0, 1.0, 4.0, 20.0), 0, 0, false));
        players.add(
                new Player("Shashi Henra", team, Arrays.asList(30.0, 25.0, 5.0, 0.0, 5.0, 1.0, 4.0, 30.0), 0, 0, false));
        rules = new Rules[]{new PlayerOut(), new StrikeChange()};
        randomRunsStrategy.setRules(rules);

        try {
            List<Player> updatedPlayers = gameSimulator.applyRules(players);
            for (Player p : updatedPlayers) {
                System.out.println(p.getName()+" - "+p.getTotalRuns()+(p.isOut()?"":"*")+" ("+p.getTotalBallsPlayed()+" balls)");
            }
        } catch (NoPlayersException | InvalidTeamException exp) {
            System.out.println(exp);
        }
    }
}
