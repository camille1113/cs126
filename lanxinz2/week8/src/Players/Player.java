package Players;

import ninja.zilles.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * this class implements the player strategy
 * Created by zhanglanxin on 3/6/17.
 */
public class Player implements PlayerStrategy {
    public boolean isSmart;
    private int yourPlayerNumber;
    private int totalNumberOfPlayers;
    private List<RecordedPlay> playRecords = new ArrayList<>();
    private HashMap<Integer, AdvancedHand> currentHands = new HashMap<>();

    /**
     * this method is used to update the current state of the game by
     * pass the cards on each of the player' hand
     *
     * @param hands the list of cards on each player's hand
     */
    public void currentState(HashMap<Integer, AdvancedHand> hands) {
        currentHands = hands;
    }


    @java.lang.Override
    public void initialize(int yourPlayerNumber, int totalNumberOfPlayers) {
        this.yourPlayerNumber = yourPlayerNumber;
        this.totalNumberOfPlayers = totalNumberOfPlayers;
    }

    /**
     * The method invoked on a player each time it is their turn to act.
     *
     * @param hand The current state of the player's hand when they are to act
     * @return The action they choose to take in response to their current hand/game state
     */
    @java.lang.Override
    public Play doTurn(Hand hand) {
        //if the player is naive or it is the first play, return random play
        if (!isSmart || playRecords.isEmpty())
            return randomPlay(hand);
        //choose a random card at first
        int randomCardIndex = (int) (Math.random() * hand.size());
        int targetRank = hand.getCard(randomCardIndex).getRank();
        //choose a random opponent at first
        int opponent = (int) (Math.random() * totalNumberOfPlayers);
        //prevent the opponent to be self and prevent opponent out of game
        while (opponent == yourPlayerNumber || !currentHands.containsKey(opponent)) {
            opponent = (int) (Math.random() * totalNumberOfPlayers);
        }
        int lastTargetRank = playRecords.get(playRecords.size() - 1).getRank();
        int lastSourcePlayer = playRecords.get(playRecords.size() - 1).getSourcePlayer();
        //to prevent the player ask for the same opponent again
        boolean askAgain = ((lastSourcePlayer == yourPlayerNumber) && (lastTargetRank == targetRank));
        boolean hasOpponent = false;
        boolean hasMaxOccurrence = false;
        //try to find the card with highest occurrence in the hand
        ArrayList<Integer> ranks = new ArrayList<>();
        for (Card card : hand) {
            ranks.add(card.getRank());
        }
        int maxOccurrence = 1; //use to compare the frequency of each card in the hand
        for (int i = 0; i < ranks.size(); i++) {
            if (Collections.frequency(ranks, ranks.get(i)) > maxOccurrence) {
                hasMaxOccurrence = true;
                //choose the rank with highest occurrence in the hand to be the target rank
                if (getOpponent(ranks.get(i)) != -1) {
                    opponent = getOpponent(ranks.get(i));
                    targetRank = ranks.get(i);
                    maxOccurrence = Collections.frequency(ranks, ranks.get(i));
                } else {
                    int removeRank = ranks.get(i);
                    for (int j = i; j < ranks.size(); j++) {
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
        for (Card card : hand) {
            ranks.add(card.getRank());
        }

        List<Integer> rankOccurrence = new ArrayList<>();
        //if all cards only occurred once
        if (!hasMaxOccurrence) {
            for (Integer i : ranks) {
                int lastOccurrence = findLastOccurrence(i);
                if (lastOccurrence != -1 && !askAgain) {
                    rankOccurrence.add(lastOccurrence);
                    hasOpponent = true;
                }
            }
        }
        System.out.println("test");
        if (hasOpponent) {
            int theLast = Collections.max(rankOccurrence);
            opponent = playRecords.get(theLast).getSourcePlayer();
            targetRank = playRecords.get(theLast).getRank();
        } else {
            //if none of the other player has asked for the card
            return randomPlay(hand);
        }
        return new Play(opponent, targetRank);
    }

    /**
     * this method find a random opponent to ask for a card
     * @param hand the cards on this player's hand
     * @return play with random opponent and random card
     */
    public Play randomPlay(Hand hand) {
        //pick a random opponent
        int opponent = (int) (Math.random() * totalNumberOfPlayers);
        while (opponent == yourPlayerNumber || !currentHands.containsKey(opponent)) {
            opponent = (int) (Math.random() * totalNumberOfPlayers);
        }

        //pick a random card from this player's hand
        int randomCardIndex = (int) (Math.random() * hand.size());
        return new Play(opponent, hand.getCard(randomCardIndex).getRank());
    }

    /**
     * this method find the last occurrence during the play for a card
     * @param targetRank the target rank
     * @return the number of the last occurrence in the play records
     */
    public int findLastOccurrence(int targetRank) {
        int lastTargetRank = playRecords.get(playRecords.size() - 1).getRank();
        int lastSourcePlayer = playRecords.get(playRecords.size() - 1).getSourcePlayer();
        int lastOccurrence = -1;
        //to prevent the player ask for the same opponent again
        boolean askAgain = ((lastSourcePlayer == yourPlayerNumber) && (lastTargetRank == targetRank));
        for (int i = 0; i < playRecords.size(); i++) {
            //if a player asked for a card it means that player has the rank
            if (playRecords.get(i).getRank() == targetRank) {
                if ((playRecords.get(i).getSourcePlayer() != yourPlayerNumber) && (!askAgain)
                        && (currentHands.containsKey(playRecords.get(i).getSourcePlayer())))
                    lastOccurrence = i;
            }
        }
        return lastOccurrence;
    }

    /**
     * this method go through all the plays occurred to find
     * if a player has asked for a card with the targeted rank of this current player
     *
     * @param targetRank the target rank
     * @return the number of the target player who has asked for the rank before
     */
    public int getOpponent(int targetRank) {
        int lastTargetRank = playRecords.get(playRecords.size() - 1).getRank();
        int lastSourcePlayer = playRecords.get(playRecords.size() - 1).getSourcePlayer();
        //to prevent the player ask for the same opponent again
        boolean askAgain = ((lastSourcePlayer == yourPlayerNumber) && (lastTargetRank == targetRank));
        for (RecordedPlay recordedPlay : playRecords) {
            //if a player asked for a card it means that player has the rank
            if (recordedPlay.getRank() == targetRank) {
                if ((recordedPlay.getSourcePlayer() != yourPlayerNumber) && (!askAgain)
                        && (currentHands.containsKey(recordedPlay.getSourcePlayer())))
                    return recordedPlay.getSourcePlayer();
            }
        }
        return -1;
    }

    /**
     * Every time a game action takes place, the game engine invokes the following function on each player.
     *
     * @param recordedPlay an object representing the information of the play that just occurred and its results.
     */
    @java.lang.Override
    public void playOccurred(RecordedPlay recordedPlay) {
        playRecords.add(recordedPlay);
    }

}
