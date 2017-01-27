import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 1/22/17.
 */
public class TicTacToeTest {
    @Test
    public void getString() throws Exception{
        String[] input1 = {"OOO", "OOO", "OOO"};
        String[] input2 = {"OXO", "XOX", "OXO"};
        String[] input3 = {"OXX", "XOX", "OXX"};
        String[] input4 = {"...", "XOX", "OXX"};
        String[] input5 = {"X.O", "XXX", "OOO"};
        assertEquals( TicTacToe.temp(input1),"Player O wins!");
        assertEquals(TicTacToe.temp(input2),"Player O wins!");
        assertEquals(TicTacToe.temp(input3),"Player X wins!");
        assertEquals(TicTacToe.temp(input4),"No winner");
        assertEquals(TicTacToe.temp(input5),"Player O wins!");
    }

}