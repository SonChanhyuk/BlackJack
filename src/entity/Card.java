package entity;

public class Card extends Entity {
    public static final String SPADES = "spades";
    public static final String HEARTS = "hearts";
    public static final String DIAMONDS = "diamonds";
    public static final String CLUBS = "clubs";

    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int SIZE_OF_ONE_SUIT = 13;

    private String suit;
    private int rank;

    public Card(String s, int r) {
        this.suit = s;
        this.rank = r;
        String name;
        if (rank == ACE)
            name = "ace";
        else if (rank == JACK)
            name = "jack";
        else if (rank == QUEEN)
            name = "queen";
        else if (rank == KING)
            name = "king";
        else
            name = Integer.toString(this.rank);
        this.route = "res/PNG-cards-1.3/" + name +"_of_" + this.suit + ".png";
    }

    public String getSuit() { return this.suit; }

    public int getRank() { return this.rank; }


    //확인용
    public void printCard() {
        System.out.print(suit);
        System.out.print(" ");
        if (rank == ACE)
            System.out.print("A");
        else if (rank == JACK)
            System.out.print("J");
        else if (rank == QUEEN)
            System.out.print("Q");
        else if (rank == KING)
            System.out.print("K");
        else
            System.out.print(rank);
        System.out.print("\n");
    }
}
