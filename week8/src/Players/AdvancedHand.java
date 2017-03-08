package Players;

import ninja.zilles.Card;
import ninja.zilles.Hand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglanxin on 3/6/17.
 */
public class AdvancedHand extends Hand {
    public AdvancedHand(List<Card> cards) {
        super(cards);
    }
    public void add(Card newCard){
        cards.add(newCard);
    }
    public void remove(Card removeCard){
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i).getShortName().equals(removeCard.getShortName())){
                cards.remove(i);
            }
        }
    }
}
