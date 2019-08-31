package exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvalidTeamExceptionTest {
    @Test
    public void invalidTeamExceptionTest(){
        InvalidTeamException invalidTeamException = new InvalidTeamException();
        assertEquals("\n\033[31;1mTeam is Invalid as overs or wickets or runs to win are less than 1 or teams name might be not provided\033[0m",invalidTeamException.toString());
    }
}
