package Utils;

import model.Team;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ValidationUtilsTest {
    private Team team;
    @Test
    public void isInvalidTeamWhenGivenProperTeamTest(){
        team = new Team("Bengaluru", "Chennai", 4, 40, 4);
        assertFalse(ValidationUtils.isInvalidTeam(team));
    }
    @Test
    public void isInvalidTeamWhenGivenEmptyTeamTest(){
        team = new Team("", "Chennai", 4, 40, 4);
        assertTrue(ValidationUtils.isInvalidTeam(team));
    }
}
