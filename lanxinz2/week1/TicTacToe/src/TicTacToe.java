/**
 * Created by zhanglanxin on 1/17/17.
 */
public class TicTacToe {
    public static void main(String[] args) {
        if(args[0].equals("OOO")||args[1].equals("OOO")||args[2].equals("OOO")
                ||(args[0].charAt(0)=='O' && args[1].charAt(1)=='O' && args[2].charAt(2)=='O')
                ||(args[0].charAt(2)=='O' && args[1].charAt(1)=='O' && args[2].charAt(0)=='O')
                ||(args[0].charAt(0)=='O' && args[1].charAt(0)=='O' && args[2].charAt(0)=='O')
                ||(args[0].charAt(1)=='O' && args[1].charAt(1)=='O' && args[2].charAt(1)=='O')
                ||(args[0].charAt(2)=='O' && args[1].charAt(2)=='O' && args[2].charAt(2)=='O'))
            System.out.println("Player O wins!");
        if(args[0].equals("XXX")||args[1].equals("XXX")||args[2].equals("XXX")
                ||(args[0].charAt(0)=='X' && args[1].charAt(1)=='X' && args[2].charAt(2)=='X')
                ||(args[0].charAt(2)=='X' && args[1].charAt(1)=='X' && args[2].charAt(0)=='X')
                ||(args[0].charAt(0)=='X' && args[1].charAt(0)=='X' && args[2].charAt(0)=='X')
                ||(args[0].charAt(1)=='X' && args[1].charAt(1)=='X' && args[2].charAt(1)=='X')
                ||(args[0].charAt(2)=='X' && args[1].charAt(2)=='X' && args[2].charAt(2)=='X'))
            System.out.println("Player X wins!");
        else
            System.out.println("No winner.");
    }
}
