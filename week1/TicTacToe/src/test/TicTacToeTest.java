import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 1/22/17.
 */
public class TicTacToeTest {
    @Test
    //test rows
    public void rowTest() throws Exception{
        String[] input1 = {"OOO", "XXO", "OXO"};
        assertEquals( TicTacToe.temp(input1),"Player O wins!");
        String[] input2 = {"X.O", "XXX", "OOO"};
        assertEquals( TicTacToe.temp(input2),"Player X wins!");
    }
    @Test
    //test columns
    public void colTest() throws Exception {
        String[] input = {"OXX", "XOX", "OXX"};
        assertEquals(TicTacToe.temp(input),"Player X wins!");
    }
    @Test
    public void diaTest() throws Exception {
        String[] input1 = {"OXX", "XOX", "OXO"};
        assertEquals(TicTacToe.temp(input1),"Player O wins!");
        String[] input2 = {"XXO", "XOX", "OX."};
        assertEquals(TicTacToe.temp(input2),"Player O wins!");

    }
    @Test
    //test for no winners
    public void noWin() throws Exception{
        String[] input = {"...", "XOX", "OXX"};
        assertEquals(TicTacToe.temp(input),"There is no winner.");
    }
}