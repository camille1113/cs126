/**
 * Created by zhanglanxin on 1/22/17.
 */
public class TicTacToe {
    public static void main(String[] args){
        temp(args);
    }
    //a method to get the string for the test
    public static String temp(String[] args) {
        return getString(args);
    }
    //this method test which player wins
    public static String tester(String x){
        for(int i = 0; i < 9; i += 3) {
            if (x.substring(i, i + 3).equals("OOO")) {
                return "Player O wins!";
            }
            else if(x.substring(i, i + 3).equals("XXX")) {
                return "Player X wins!";
            }
        }
        return "There is no winner.";
    }
    //this method rearranges the args in order of rows, columns and diagonals
    //and convert it to a string
    public static String getString(String[] args) {

        // for rows
        StringBuilder row = new StringBuilder();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j< 3; j++) {
                row.append(args[i].charAt(j));
            }
        }
        String result1 = row.toString();
        if(!tester(result1).equals("There is no winner."))
            return tester(result1); //only return if it finds which one wins
                                    //since there are other possibilities left untested

        // for columns
        StringBuilder col = new StringBuilder();
        for(int m = 0; m < 3; m++){
            for(int n = 0; n < 3; n++) {
                col.append(args[n].charAt(m));
            }
        }
        String result2 = col.toString();
        if(!tester(result2).equals("There is no winner."))
            return tester(result2); //only return if it finds which one wins
                                    //since there are other possibilities left untested

        // for diagonals
        StringBuilder dia = new StringBuilder();
        for(int k = 0; k < 3; k++) {
            dia.append(args[k].charAt(k));
        }
        String result3 = dia.toString()+"......"; //add six "."s to prevent StringOutOfBoundsException
        if(!tester(result3).equals("There is no winner."))
            return tester(result3);

        for(int k = 0; k < 3; k++) {
            dia.append(args[k].charAt(2 - k));
        }
        String result4 = dia.toString()+"......"; //add six "."s to prevent StringOutOfBoundsException
            return tester(result4); //all possibilities have been tested, just return the last result


    }
}


