package Players;

import ninja.zilles.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhanglanxin on 3/6/17.
 */
public class Player implements PlayerStrategy {
    public int yourPlayerNumber;
    public int totalNumberOfPlayers;
    public boolean isSmart;
    public List<RecordedPlay> playRecords = new ArrayList<>();
    public HashMap<Integer, AdvancedHand> currentHands = new HashMap<>();
    public void currentState(HashMap<Integer, AdvancedHand> hands){
        currentHands = hands;
    }


    @java.lang.Override
    /**
     * The first function called on the object to provide information that is true for the whole execution.
     * @param yourPlayerNumber a number between 0 and (totalNumberOfPlayers - 1) specifying the current player
     * @param totalNumberOfPlayers a positive integer indicating the total number of players in the game
     */
    public void initialize(int yourPlayerNumber, int totalNumberOfPlayers) {
        this.yourPlayerNumber = yourPlayerNumber;
        this.totalNumberOfPlayers = totalNumberOfPlayers;
    }

    /**
     * The method invoked on a player each time it is their turn to act.
     * @param hand The current state of the player's hand when they are to act
     * @return The action they choose to take in response to their current hand/game state
     */
    @java.lang.Override
    public Play doTurn(Hand hand) {
        if(isSmart){
            //choose a random card at first
            int randomCardIndex = (int)(Math.random()*hand.size());
            int targetRank = hand.getCard(randomCardIndex).getRank();
            //choose a random opponent at first
            int opponent = (int)(Math.random()*totalNumberOfPlayers);
            //prevent the opponent to be self and prevent opponent out of game
            while(opponent==yourPlayerNumber || !currentHands.containsKey(opponent)){
                opponent = (int)(Math.random()*totalNumberOfPlayers);
            }

            //try to find the card with highest occurrence in the hand
            ArrayList<Integer> ranks = new ArrayList<>();
            for (Card card: hand) {
                ranks.add(card.getRank());
            }
            int maxOccurrence = 1; //use to compare the frequency of each card in the hand
            for (int i = 0; i < ranks.size(); i++) {
                if (Collections.frequency(ranks, ranks.get(i)) > maxOccurrence) {
                    //choose the rank with highest occurrence in the hand to be the target rank
                    if (getOpponent(ranks.get(i)) != -1) {
                        opponent = getOpponent(ranks.get(i));
                        targetRank = ranks.get(i);
                        maxOccurrence = Collections.frequency(ranks, ranks.get(i));
                    }
                    else{
                        int removeRank = ranks.get(i);
                        for(int j = i; j < ranks.size(); j++ ) {
                            if (ranks.get(j) == removeRank) {
                                ranks.remove(j);
                                j--;
                            }
                        }
                        i--;
                    }
                }
            }
            ranks.clear();
            for (Card card: hand) {
                ranks.add(card.getRank());
            }
            boolean hasOpponent = false;
            //if all cards only occurred once
            if(maxOccurrence == 1){
                for (int i = 0; i < ranks.size(); i++) {
                    if (getOpponent(ranks.get(i)) != -1) {
                        opponent = getOpponent(ranks.get(i));
                        targetRank = ranks.get(i);
                        hasOpponent = true;
                    }
                }
            }
            if(!hasOpponent) {
                for (int i = 0; i < ranks.size(); i++) {
                    if (Collections.frequency(ranks, ranks.get(i)) > maxOccurrence) {
                        //choose the rank with highest occurrence in the hand to be the target rank
                        targetRank = ranks.get(i);
                        maxOccurrence = Collections.frequency(ranks, ranks.get(i));
                    }
                }
            }
            //System.out.println("target rank  " + targetRank + "   opponent  " +  opponent);
            Play play = new Play(opponent,targetRank);
            return play;

        }
        else {
            //pick a random opponent
            int opponent = (int)(Math.random()*totalNumberOfPlayers);
            while(opponent==yourPlayerNumber || !currentHands.containsKey(opponent)){
                opponent = (int)(Math.random()*totalNumberOfPlayers);
            }

            //pick a random card from this player's hand
            int randomCardIndex = (int)(Math.random()*hand.size());
            Play play = new Play(opponent,hand.getCard(randomCardIndex).getRank());
            return play;

        }


    }

    /**
     * this method go through all the plays occurred to find
     * if a player has asked for a card with the targeted rank of this current player
     * @param targetRank
     * @return the number of the target player who has asked for the rank before
     */
    public int getOpponent(int targetRank) {
        int lastTargetRank = playRecords.get(playRecords.size()-1).getRank();
        int lastSourcePlayer = playRecords.get(playRecords.size()-1).getSourcePlayer();
        //to prevent the player ask for the same opponent again
        boolean askAgain = ((lastSourcePlayer == yourPlayerNumber) && (lastTargetRank == targetRank));
        for(int i = 0; i < playRecords.size(); i++)  {
            //if a player asked for a card it means that player has the rank
            if(playRecords.get(i).getRank() == targetRank ) {
                if ((playRecords.get(i).getSourcePlayer() != yourPlayerNumber) && (!askAgain)
                        && (currentHands.containsKey(playRecords.get(i).getSourcePlayer())))
                    return playRecords.get(i).getSourcePlayer() ;
            }
        }
        return -1;
    }

    /**
     * Every time a game action takes place, the game engine invokes the following function on each player.
     * @param recordedPlay an object representing the information of the play that just occurred and its results.
     */
    @java.lang.Override
    public void playOccurred(RecordedPlay recordedPlay) {
        playRecords.add(recordedPlay);
    }
}
