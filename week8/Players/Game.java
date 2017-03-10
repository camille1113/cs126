package Players;
import ninja.zilles.*;
import java.util.*;

/**
 * this class plays the go fish game
 * Created by zhanglanxin on 3/6/17.
 */
public class Game {
    public static int playGame(int numberOfPlayers, HashMap<Integer, Player> players) {
        int[] bookMade = {0};
        int[] deckSize = {52};
        Deck deck = new Deck();

        //initialize each player's point to 0
        HashMap<Integer, Integer> points = new HashMap<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            points.put(i, 0);
        }

        //initialize the AdvancedHand of each player;
        HashMap<Integer, AdvancedHand> hands = new HashMap<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            List<Card> initialCards = deal(5, deck, deckSize);
            AdvancedHand hand = new AdvancedHand(initialCards);
            hands.put(i, hand);
        }
        for(Player player: players.values()){
            player.currentState(hands);
        }

        while (!hands.isEmpty() && bookMade[0] != 13) {
            int playerNumber = 0;
            while (playerNumber < numberOfPlayers) {
                if(hands.containsKey(playerNumber)) {
                    Player currentPlayer = players.get(playerNumber);
                    while (oneTurn(deck, deckSize, points, bookMade, hands, currentPlayer, playerNumber, players)) ;
                }
                playerNumber++;
            }
        }
        for (int i = 0; i < players.size(); i++) {
            System.out.println((players.get(i).isSmart?"Smart":"Naive") + "Player" + i + " had " +
                    points.get(i) + " points");
        }

        // find the highest points
        int highestPoint = (Collections.max(points.values()));
        int SmartWinners = 0;
        int NaiveWinners = 0;
        // find the player with highest points
        for (Integer i: players.keySet()) {
            if (points.get(i) == highestPoint) {
                boolean isSmart = players.get(i).isSmart;
                System.out.println((isSmart?"Smart":"Naive") + "Player" + i + " won!");
                if(isSmart)
                    SmartWinners++;
                else
                    NaiveWinners++;
            }
        }
        if(SmartWinners > NaiveWinners)
            return 1;
        else if(SmartWinners < NaiveWinners)
            return -1;
        else
            return 0;
    }

    /**
     * this method plays one turn of the game
     * the current player will ask for another player for a rank
     * if the other player doesn't have this rank, current player
     * go fish and returns false, else if the deck is empty and the current
     * player does no cards left returns false, else the current player get the
     * target card from the other player and continue to ask for cards
     *
     * @param deck          current deck being used to play the gamee
     * @param deckSize      the cards left in the deck
     * @param points        the points of each player
     * @param hands         the cards on each player's hand
     * @param currentPlayer current player
     * @return true if current player get a card, false otherwise
     */
    public static boolean oneTurn(Deck deck, int[] deckSize, HashMap<Integer, Integer> points, int[] bookMade,
                                  HashMap<Integer, AdvancedHand> hands, Player currentPlayer,
                                  int playerNumber, HashMap<Integer, Player> players) {

        boolean goAgain = false;
        AdvancedHand currentHand = hands.get(playerNumber);
        //deal cards to the current player if current hand is empty
        if (currentHand.size() == 0) {
            if (deck.isEmpty()) {
                //if the player has no cards and the deck is empty
                //continue to next player
                System.out.println((currentPlayer.isSmart?"Smart":"Naive") + "Player" + playerNumber + "" +
                        " is out of game");
                hands.remove(playerNumber);
                return false;
            }
            else {
                List<Card> initialCards;
                //if less than 5 cards remain in the deck
                //give it all to the current player
                if (deckSize[0] < 5) {
                    initialCards = deal(deckSize[0], deck, deckSize);
                } else {
                    initialCards = deal(5, deck, deckSize);
                }
                currentHand = new AdvancedHand(initialCards);
                hands.put(playerNumber, currentHand);
            }
        }

        Play currentTurn = currentPlayer.doTurn(currentHand);
        int targetPlayerNumber = currentTurn.getTargetPlayer();
        int targetRank = currentTurn.getRank();

        List<Card> cardsReturned = new ArrayList<>();
        RecordedPlay currentPlay = new RecordedPlay(playerNumber, targetPlayerNumber,
                targetRank, cardsReturned);

        AdvancedHand targetHand = hands.get(targetPlayerNumber);

        //if the target player does not have the target rank
        //current player will go fish
        if (!targetHand.hasRank(targetRank)) {
            System.out.println((currentPlayer.isSmart?"Smart":"Naive") + "Player" + playerNumber + " asks " +
                    (players.get(targetPlayerNumber).isSmart?"Smart":"Naive") + "Player" + targetPlayerNumber + " for " +
                    Card.CARD_NAMES[targetRank] + "s and got " + 0 + " card(s)");
            if (deckSize[0] > 0) {
                Card newCard = deck.draw();
                deckSize[0] -= 1;
                currentHand.add(newCard);
                hands.put(playerNumber, currentHand);
            }
        }
        //when target player has the target rank
        else {
            int numberOfTargetCards = 0; //count how many cards the player can get
            for(int i = 0; i < targetHand.size(); i++) {
                Card currentCard = targetHand.getCard(i);
                if(!targetHand.hasRank(targetRank))
                    break;
                if (currentCard.getRank() == targetRank) {
                    //add the card to source player
                    currentHand.add(currentCard);
                    //add the card to cardsReturned for record
                    cardsReturned.add(currentCard);
                    //remove the card from the target
                    targetHand.remove(currentCard);
                    //increase the number of cards got
                    numberOfTargetCards++;
                    i--;
                }
            }
            System.out.println((currentPlayer.isSmart?"Smart":"Naive") + "Player" + playerNumber + " asks " +
                    (players.get(targetPlayerNumber).isSmart?"Smart":"Naive") + "Player" + targetPlayerNumber + " for " +
                    Card.CARD_NAMES[targetRank] + "s and got " + numberOfTargetCards + " card(s)");
            goAgain = true;
        }
        //check if the player made a book after getting new cards
        hasMadeBook(points, bookMade, hands, playerNumber, currentHand, currentPlayer);
        //add current play to each player's record before return
        for(Player player: players.values()){
            player.playOccurred(currentPlay);
            player.currentState(hands);
        }
        return goAgain;
    }

    /**
     * this method check if the cards on this hand has made a book
     * and if it has made a book, add one point to current player,
     * add one to bookMade, remove the book and update the hands
     * @param points  each player's current points
     * @param bookMade the number of how many books has made
     * @param hands each player's hand of cards
     * @param playerNumber current player's player number
     * @param currentHand the hand of cards of the current player
     * @param currentPlayer current player
     */
    public static void hasMadeBook(HashMap<Integer, Integer> points, int[] bookMade, HashMap<Integer,
            AdvancedHand> hands, int playerNumber, AdvancedHand currentHand, Player currentPlayer) {
        int bookRank = findBookRank(hands.get(playerNumber));
        if (bookRank != -1) {
            System.out.println((currentPlayer.isSmart?"Smart":"Naive") + "Player" + playerNumber +
                    " made a book of " + Card.CARD_NAMES[bookRank] + "s");
            int currentPoint = points.get(playerNumber) + 1;
            bookMade[0]++;
            //add 1 point to the player if a book is made
            points.put(playerNumber, currentPoint);
            //remove the book from the current hand
            hands.put(playerNumber, removeBook(bookRank, currentHand));
        }
    }

    /**
     * this method deals cards by the given number from the deck
     *
     * @param numberOfCards required by the game
     * @param deck          the deck the game is using
     * @return cards that are draw from the deck
     */
    public static List<Card> deal(int numberOfCards, Deck deck, int[] deckSize) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            cards.add(deck.draw());
            deckSize[0] -= 1;
        }
        return cards;
    }

    /**
     * this method count the occurence of each rank in a hand
     * if a rank occurs 4 times, then it makes a book and return this
     * rank, else it returns 0 if no book is made
     *
     * @param hand current hand that need to be checked
     * @return the rank of the cards that made a book, -1 if no book made
     */
    public static int findBookRank(AdvancedHand hand) {
        if (hand.size() < 4)
            return -1;
        ArrayList<Integer> ranks = new ArrayList<>();
        for (Card card: hand) {
            ranks.add(card.getRank());
        }
        for (Integer i : ranks) {
            if (Collections.frequency(ranks, i) == 4) {
                return i;
            }
        }
        return -1;
    }

    /**
     * this method removes a book from a hand
     *
     * @param rank the rank of the book that is needed to be removed
     * @param hand the hand that the book needs to be removed from
     * @return the rest of the hand
     */
    public static AdvancedHand removeBook(int rank, AdvancedHand hand) {
        for(int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.getCard(i);
            if (currentCard.getRank() == rank) {
                //remove the card from the target
                hand.remove(currentCard);
                i--;
            }
        }
        return hand;
    }
}
