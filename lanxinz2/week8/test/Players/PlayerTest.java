package Players;

import ninja.zilles.Card;
import ninja.zilles.Play;
import ninja.zilles.RecordedPlay;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhanglanxin on 3/10/17.
 */
public class PlayerTest {
    HashMap<Integer, AdvancedHand> testHands = new HashMap<>();
    HashMap<Integer, Player> testPlayers = new HashMap<>();
    List<RecordedPlay> playRecords = new ArrayList<>();
    List<Card> cardsReturned = new ArrayList<>();
    List<Card> initialCards = new ArrayList<>();

    public void testData() {

        //player0 : A-S,1-H,2-D,3-C,2-C
        int i = 0;
        for (Card.Suit suit : Card.Suit.values()) {
            initialCards.add(new Card(i, suit));
            i += 1;
        }
        initialCards.add(new Card(1, Card.Suit.CLUBS));
        AdvancedHand hand0 = new AdvancedHand(initialCards);
        testHands.put(0, hand0);

        //player1 : 4-S,5-H,6-D,7-C,2-S
        initialCards.clear();
        for (Card.Suit suit : Card.Suit.values()) {
            initialCards.add(new Card(i, suit));
            i += 1;
        }
        initialCards.add(new Card(1, Card.Suit.SPADES));
        AdvancedHand hand1 = new AdvancedHand(initialCards);
        testHands.put(1, hand1);

        //player2 : 8-S,9-H,10-D,J-C,3-S
        initialCards.clear();
        for (Card.Suit suit : Card.Suit.values()) {
            initialCards.add(new Card(i, suit));
            i += 1;
        }
        initialCards.add(new Card(2, Card.Suit.SPADES));
        AdvancedHand hand2 = new AdvancedHand(initialCards);
        testHands.put(2, hand2);

        //player3 : K-S,Q-H,J-D,10-C,3-C
        i = 12;
        initialCards.clear();
        for (Card.Suit suit : Card.Suit.values()) {
            initialCards.add(new Card(i, suit));
            i -= 1;
        }
        initialCards.add(new Card(3, Card.Suit.HEARTS));
        AdvancedHand hand3 = new AdvancedHand(initialCards);
        testHands.put(3, hand3);


        for (int k = 0; k < 4; k++) {
            Player player = new Player();
            player.initialize(k, 4);
            player.isSmart = false;
            player.currentState(testHands);
            testPlayers.put(k, player);
        }
        for(Player player: testPlayers.values()){
            player.playOccurred(new RecordedPlay(1, 0, 3, cardsReturned));
            player.playOccurred(new RecordedPlay(1, 3, 3, cardsReturned));
            player.playOccurred(new RecordedPlay(0, 2, 2, cardsReturned));
            player.playOccurred(new RecordedPlay(1, 1, 3, cardsReturned));
        }
    }


    @Test
    public void doTurn() throws Exception {
        testData();
        Play play0 = testPlayers.get(0).doTurn(testHands.get(0));
        Play play1 = testPlayers.get(1).doTurn(testHands.get(1));
        //won't ask for card he doesn't have
        //won't ask self for card
        assertFalse(play0.getRank()==4);
        assertFalse(play0.getTargetPlayer()==0);
        assertFalse(play1.getTargetPlayer()==1);
        assertFalse(play1.getRank()==13);
    }

    @Test
    public void randomPlay() throws Exception {
        testData();
        Play play2 = testPlayers.get(2).doTurn(testHands.get(2));
        Play play3 = testPlayers.get(3).doTurn(testHands.get(3));
        //won't ask for card he doesn't have
        //won't ask self for card
        assertFalse(play2.getRank()==4);
        assertFalse(play2.getTargetPlayer()==2);
        assertFalse(play3.getTargetPlayer()==3);
        assertFalse(play3.getRank()==8);
    }

    @Test
    public void findLastOccurrence() throws Exception {
        testData();
        Player player0 = testPlayers.get(0);
        Player player1 = testPlayers.get(1);
        assertEquals(player0.findLastOccurrence(3),3);
        assertEquals(player0.findLastOccurrence(10),-1);
        assertEquals(player1.findLastOccurrence(2),2);
        assertEquals(player1.findLastOccurrence(1),-1);
    }

    @Test
    public void getOpponent() throws Exception {
        testData();
        Player player0 = testPlayers.get(0);
        Player player1 = testPlayers.get(1);
        assertEquals(player0.getOpponent(3),1);
        assertEquals(player0.getOpponent(10),-1);
        assertEquals(player1.getOpponent(2),0);
        assertEquals(player1.getOpponent(3),-1);
    }



}