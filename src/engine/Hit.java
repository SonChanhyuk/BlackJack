package engine;

import entity.Card;
import entity.CardDeck;
import entity.CardPlayer;

public class Hit {
    private CardPlayer player;
    private CardDeck cd;
    private Card c;
    public Hit(CardPlayer cardplayer, CardDeck carddeck) {
        player = cardplayer;
        cd = carddeck;
    }

    //hit 전에 카드덱 상태확인
    public void hitcard() {
        c = cd.newCard();
        player.receiveCard(c);
    }
}
