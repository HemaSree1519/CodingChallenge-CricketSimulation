package exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoPlayersExceptionTest {
    @Test
    public void noPlayerExceptionsTest(){
        NoPlayersException noPlayersException = new NoPlayersException();
        assertEquals("\n\033[31;1mTeam size is insufficient\033[0m", noPlayersException.toString());
    }
}
