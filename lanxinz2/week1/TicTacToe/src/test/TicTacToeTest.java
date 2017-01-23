import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 1/22/17.
 */
public class TicTacToeTest {
    @Test
    public void getString() throws Exception {
        String [] input1 = {"OOO","OOO","OOO"};
        String [] input2 = {"OXO","XOX","OXO"};
        String [] input3 = {"OXX","XOX","OXX"};
        String [] input4 = {"...","XOX","OXX"};
        assertTrue(TicTacToe.main(input1).equals("Player O wins!"));
        assertTrue(TicTacToe.main(input2).equals("Player O wins!"));
        assertFalse(TicTacToe.main(input2).equals("Player X wins!"));
        assertTrue(TicTacToe.main(input3).equals("Player X wins!"));
        assertTrue(TicTacToe.main(input4).equals("No winner"));
    }

}