package screen;
import entity.Card;
import entity.ComputerPlayer;
import entity.HumanPlayer;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends Screen{

    private final int BUTTON_WIDTH = 180;
    private final int BUTTON_HEIGHT = 90;
    private JPanel background;
    private JButton deal;
    private JButton hit;
    private JButton stand;
    private JButton end;
    private JLabel chipLbl;
    private JLabel[] playerCards;
    private JLabel[] dealerCards;
    private HumanPlayer player;
    private ComputerPlayer dealer;

    public GameScreen(boolean isRun, HumanPlayer human, ComputerPlayer computer) {
        super(1,1280,720, isRun);
        player = human;
        dealer = computer;
        container.setLayout(null);
        playerCards = new JLabel[player.getMax_hand()];
        dealerCards = new JLabel[dealer.getMax_hand()];
        chipLbl = new JLabel();
        chipLbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        chipLbl.setBounds(1050,30, 200, 50);
        container.add(chipLbl);
        MakeButton();
    }

    private void MakeButton(){
        deal = new JButton("Deal");
        deal.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        deal.setBounds(70, height/2-BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        container.add(deal);

        hit = new JButton("Hit");
        hit.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        hit.setBounds(390, height/2-BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        container.add(hit);

        stand = new JButton("Stand");
        stand.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        stand.setBounds(710, height/2-BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        container.add(stand);

        end = new JButton("End");
        end.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        end.setBounds(1030, height/2-BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        container.add(end);

    }

    public void ShowCards(int order,boolean isPlayer ,Card card){
        if(isPlayer){
            ImageIcon icon = new ImageIcon(card.getRoute());
            System.out.println(card.getRoute());
            Image image = icon.getImage().getScaledInstance(150,200,Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            playerCards[order] = new JLabel(icon);
            container.add(playerCards[order]);
            playerCards[order].setBounds(70+(player.getCard_count()-1)*200,440,150,200);
            playerCards[order].setVisible(true);
        }
        else{
            ImageIcon icon;
            if (order == 0){ icon = new ImageIcon("res/card_back.png"); }
            else { icon = new ImageIcon(card.getRoute()); }
            Image image = icon.getImage().getScaledInstance(150,200,Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            dealerCards[order] = new JLabel(icon);
            container.add(dealerCards[order]);
            dealerCards[order].setBounds(70+(dealer.getCard_count()-1)*200,30,150,200);
            dealerCards[order].setVisible(true);
        }
    }

    public void RevealDealerCard(){
        ImageIcon icon = new ImageIcon(dealer.getMy_hand()[0].getRoute());
        Image image = icon.getImage().getScaledInstance(150,200,Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        dealerCards[0].setIcon(icon);
    }

    public void ResetField(){
        System.out.println(player.getChips());
        ShowChips();
        for (int i=0;i<player.getCard_count();i++){
            playerCards[i].setVisible(false);
        }
        for (int i=0;i<dealer.getCard_count();i++){
            dealerCards[i].setVisible(false);
        }
        deal.setEnabled(true);
        hit.setEnabled(false);
        stand.setEnabled(false);
    }

    public void ShowChips(){
        chipLbl.setText("보유한 칩:" + player.getChips());
    }

    public void setPlayer(HumanPlayer humanPlayer) { this.player = humanPlayer; }
    public HumanPlayer getPlayer() { return this.player; }
    public ComputerPlayer getDealer() { return this.dealer; }
    public JButton getDeal(){ return this.deal; }
    public JButton getHit(){ return this.hit; }
    public JButton getStand(){ return this.stand; }
    public JButton getEnd(){ return this.end; }
}
