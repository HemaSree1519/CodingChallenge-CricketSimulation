package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StateTest {

    State state;

    @Before
    public void setUp() throws Exception {
        state = new State("Kirat Boli", "NS Nodhi", 4, 3, 5, 25, true, 0);
    }

    @Test
    public void getCurrentStrikerTest() {
        assertEquals("Kirat Boli", state.getCurrentStriker());
    }

    @Test
    public void getCurrentNonStrikerTest() {
        assertEquals("NS Nodhi", state.getCurrentNonStriker());
    }

    @Test
    public void getCurrentRunCountTest() {
        assertEquals(4, state.getCurrentRunCount());
    }

    @Test
    public void getCurrentWicketLeftTest() {
        assertEquals(3, state.getCurrentWicketLeft());
    }

    @Test
    public void getCurrentBallsPlayedTest() {
        assertEquals(5, state.getCurrentBallsPlayed());
    }

    @Test
    public void getCurrentRunsToWinsTest() {
        assertEquals(25, state.getCurrentRunsToWin());
    }

    @Test
    public void isCurrentPlayerOutTest() {
        assertTrue(state.isCurrentPlayerOut());
    }

    @Test
    public void getCurrentPlayerPositionTest() {
        assertEquals(0, state.getCurrentPlayerPosition());
    }
}
