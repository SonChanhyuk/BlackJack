package entity;

import javax.swing.*;

public class CardDeck extends Entity {
    private int card_count=0; // 덱에 남아있는 카드수
    private Card[] deck = new Card[4* Card.SIZE_OF_ONE_SUIT];

    private void createSuit(String which_suit) {
        for(int i = 1; i<= Card.SIZE_OF_ONE_SUIT; i++) {
            deck[card_count] = new Card(which_suit, i);
            card_count++;
        }
    }

    public CardDeck() {
        createSuit(Card.SPADES); createSuit(Card.HEARTS);
        createSuit(Card.CLUBS); createSuit(Card.DIAMONDS);
    }

    public Card newCard() {
        Card next_card;
        if(card_count != 0 ) {
            int index = (int)(Math.random() * card_count);
            next_card = deck[index];
            // 카드를 뽑은 위치부터 앞으로 당겨 준다.
            for(int i=index+1; i<card_count; i++)
                deck[i-1] = deck[i];
            card_count--;
        }
        else {
            createSuit(Card.SPADES); createSuit(Card.HEARTS);
            createSuit(Card.CLUBS); createSuit(Card.DIAMONDS);
            JOptionPane.showMessageDialog(null, "카드를 모두 소모하여 새로운 덱을 사용합니다.");
            next_card = newCard();
        }
        return next_card;
    }
    public int getCard_count() {
        return this.card_count;
    }
}
