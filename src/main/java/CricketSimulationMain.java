import procedure.GameProcedure;
import model.Player;
import model.Team;
import rules.PlayerOut;
import rules.Rules;
import rules.StrikeChange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CricketSimulationMain {
    public static void main(String[] args) {
        Team team;
        team = new Team("Bengaluru", "Chennai", 4, 40, 4);
        List<Player> players = new ArrayList<>();
        GameProcedure gameProcedure = new GameProcedure();
        Rules[] rules;

        players.add(
                new Player("Kirat Boli", team, Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(
                new Player("NS Nodhi", team, Arrays.asList(10.0, 40.0, 20.0, 5.0, 10.0, 1.0, 4.0, 10.0), 0, 0, false));
        players.add(
                new Player("R Rumrah", team, Arrays.asList(20.0, 30.0, 15.0, 5.0, 5.0, 1.0, 4.0, 20.0), 0, 0, false));
        players.add(
                new Player("Shashi Henra", team, Arrays.asList(30.0, 25.0, 5.0, 0.0, 5.0, 1.0, 4.0, 30.0), 0, 0, false));
        rules = new Rules[]{new PlayerOut(),new StrikeChange()};
        gameProcedure.setRules(rules);
    }
}
