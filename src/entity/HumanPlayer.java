package entity;

public class HumanPlayer extends CardPlayer {
    private int chips;
    private String id;
    public HumanPlayer(int max_cards) { super(max_cards); }

    public int getChips() {
        return chips;
    }
    public void setChips(int c) {
        chips = c;
    }
    public void useChips(int c) {
        chips -= c;
    }
    public void addChips(int c) {
        chips += c;
    }
    public void setID(String ID) {
        id = ID;
    }
    public String getID() {
        return id;
    }
}
