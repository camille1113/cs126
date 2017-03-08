package ninja.zilles;
import Players.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, Player> players = new HashMap<>();
        int numberOfPlayers = args.length;
        if(numberOfPlayers == 0) {
            System.out.println("No players.");
            System.exit(0);
        }
        //add each player to naivePlayers or smartPlayers
        for(int i = 0; i < numberOfPlayers; i++){
            if(args[i].equals("naive")){
                Player player = new Player();
                player.initialize(i, numberOfPlayers);
                player.isSmart = false;
                players.put(i, player);
            }
            else if(args[i].equals("smart"))
            {
                Player player = new Player();
                player.initialize(i, numberOfPlayers);
                player.isSmart = true;
                players.put(i, player);
            }
            else{
                System.out.println("No such player" + "\n" +
                        "Please enter 'naive' or 'smart'.");
                break;
            }
        }
        //int outCome = Game.playGame(numberOfPlayers,players);
        int test = 1;
        int numberOfSmartWins = 0;
        int numberOfNaiveWins = 0;
        int numberOfDraw = 0;
        for(int i = 0; i < test; i++){
            //play the game
            int outCome = Game.playGame(numberOfPlayers,players);
            if(outCome == 1)
                numberOfSmartWins++;
            else if (outCome == -1)
                numberOfNaiveWins++;
            else
                numberOfDraw++;
        }
        int max = Math.max(Math.max(numberOfSmartWins, numberOfNaiveWins),numberOfDraw);
        String result = "";
        if(max == numberOfSmartWins)
            result = "Artificial Intelligence defeated Artificial Stupidity!";
        else if(max == numberOfNaiveWins)
            result = "Artificial Intelligence lost...";
        else
            result = "Artificial Intelligence and Artificial Stupidity draw";

        System.out.println("In " + test + " tests, Artificial Intelligence won "
        + numberOfSmartWins + " times, Artificial Stupidity won " + numberOfNaiveWins +
        " times, and draw " + numberOfDraw + " times." + "\n" + result);

    }
}
