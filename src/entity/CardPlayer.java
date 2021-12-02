package entity;

public abstract class CardPlayer extends Entity
{
    private int score;
    private Card[] my_hand;
    private int card_count;
    private int max_hand;

    public CardPlayer(int max_cards) {
        this.max_hand = max_cards;
        this.resetHand();
    }
    public void receiveCard(Card c) {
        this.my_hand[getCard_count()] = c;
        this.card_count++;
    }

    public int getCard_count() {
        return card_count;
    }
    public Card[] getMy_hand() {
        return my_hand;
    }
    public int getMax_hand() { return max_hand; }

    public void resetHand() {
        this.my_hand = new Card[this.max_hand];
        this.card_count = 0;
    }

    public int getScore() {
        score = 0;
        Card[] cd = this.getMy_hand();
        int num_of_ace = 0;
        for (int i = 0; i < this.getCard_count(); i++) {
            if (cd[i].getRank() > 1 && cd[i].getRank() <= 10) {
                score += cd[i].getRank();
            }
            else if (cd[i].getRank() > 10) {
                score += 10;
            }
            else if (cd[i].getRank() == 1) {
                num_of_ace++;
                score += 11;
            }
        }
        while (score > 21) {
            if (num_of_ace > 0) {
                num_of_ace--;
                score -= 10;
            }
            else
                break;
        }
        return score;
    }
}
