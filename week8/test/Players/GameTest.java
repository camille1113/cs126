package Players;

import ninja.zilles.Card;
import ninja.zilles.*;
import ninja.zilles.RecordedPlay;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhanglanxin on 3/10/17.
 */
public class GameTest {
    HashMap<Integer, AdvancedHand> testHands = new HashMap<>();
    HashMap<Integer, Player> testPlayers = new HashMap<>();
    List<RecordedPlay> playRecords = new ArrayList<>();
    List<Card> cardsReturned = new ArrayList<>();
    List<Card> initialCards = new ArrayList<>();
    Deck deck = new Deck();
    int[] deckSize = {52};
    HashMap<Integer, Integer> points = new HashMap<>();


    public void testData() {
        for (int i = 0; i < 4; i++) {
            points.put(i, 0);
        }
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
    public void playGame() throws Exception {
        testData();
        assertEquals(Game.playGame(4,testPlayers),-1);
    }


    @Test
    public void deal() throws Exception {
        testData();
        assertEquals(Game.deal(2, deck, deckSize).size(),2);
        assertEquals(Game.deal(3, deck, deckSize).size(),3);
        assertEquals(Game.deal(4, deck, deckSize).size(),4);
        //check if the decksize is processed correctly
        assertEquals(deckSize[0],43);
    }

    @Test
    public void findBookRank() throws Exception {
        testData();
        assertEquals(Game.findBookRank(testHands.get(0)),-1);
        assertEquals(Game.findBookRank(testHands.get(1)),-1);
        assertEquals(Game.findBookRank(testHands.get(2)),-1);
        assertEquals(Game.findBookRank(testHands.get(3)),-1);

    }

    @Test
    public void removeBook() throws Exception {
        testData();
        assertEquals(Game.removeBook(1,testHands.get(0)).size(),5);
        assertEquals(Game.removeBook(5,testHands.get(1)).size(),5);
        assertEquals(Game.removeBook(7,testHands.get(2)).size(),5);
    }

}