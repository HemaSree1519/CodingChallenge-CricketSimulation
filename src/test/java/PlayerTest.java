import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    Player player;
    @Before
    public void setUp() throws Exception {
        player = new Player("Hema");
    }
    @Test
    public void getNameTest() {
        String PlayerName = player.getName();
        assertEquals("Hema", PlayerName);
    }
}
