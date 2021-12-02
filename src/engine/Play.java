package engine;

import entity.Card;
import entity.CardDeck;
import entity.ComputerPlayer;
import entity.HumanPlayer;
import screen.GameScreen;
import screen.LoginScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Play {
    private CardDeck cardDeck;
    private LoginScreen loginScreen;
    private GameScreen gameScreen;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    private JButton DEAL;
    private JButton HIT;
    private JButton STAND;
    private JButton END;
    private int chips; // 베팅한 금액

    public Play() {
        humanPlayer = new HumanPlayer(11);
        computerPlayer = new ComputerPlayer(11);
        cardDeck = new CardDeck();
        gameScreen = new GameScreen(false, humanPlayer, computerPlayer);
        loginScreen = new LoginScreen(true, gameScreen);
        loginScreen.ViewScreen();
        ButtonActionListeners();

    }

    public void BlackJack(){
        DEAL.setEnabled(true);
        HIT.setEnabled(false);
        STAND.setEnabled(false);
        END.setEnabled(true);

    }

    private void ButtonActionListeners(){
        DEAL = gameScreen.getDeal();
        HIT = gameScreen.getHit();
        STAND = gameScreen.getStand();
        END = gameScreen.getEnd();

        DEAL.addActionListener( new ActionListener() {
            String text;
            @Override
            public void actionPerformed(ActionEvent e) {
                text = JOptionPane.showInputDialog("Enter your bet: ");
                chips = Integer.parseInt(text);
                if (chips <= gameScreen.getPlayer().getChips()){
                    gameScreen.getPlayer().useChips(chips);
                    NewGame();
                    gameScreen.ShowChips();
                }
                else { JOptionPane.showMessageDialog(null, "보유한 칩이 부족합니다.", "notice", JOptionPane.WARNING_MESSAGE); }

            }
        });

        HIT.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (humanPlayer.getScore() < 21){
                    Card card = cardDeck.newCard();
                    humanPlayer.receiveCard(card);
                    gameScreen.ShowCards(humanPlayer.getCard_count()-1, true, card);
                    if (humanPlayer.getScore() > 21){
                        gameScreen.RevealDealerCard();
                        JOptionPane.showMessageDialog(null, "버스트 되었습니다. 당신이 졌습니다.", "notice", JOptionPane.WARNING_MESSAGE);
                        gameScreen.ResetField();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "hit 를 할 수 없는 상태입니다.", "notice", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        STAND.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (computerPlayer.wantsACard()) {
                    Card card = cardDeck.newCard();
                    computerPlayer.receiveCard(card);
                    gameScreen.ShowCards(computerPlayer.getCard_count()-1, false, card);
                }
                gameScreen.RevealDealerCard();
                if (computerPlayer.getScore()>21 || humanPlayer.getScore() > computerPlayer.getScore()){
                    JOptionPane.showMessageDialog(null, "당신이 이겼습니다.", "notice", JOptionPane.WARNING_MESSAGE);
                    humanPlayer.addChips(chips*2);
                }
                else if (computerPlayer.getScore() == humanPlayer.getScore()){
                    JOptionPane.showMessageDialog(null, "무승부입니다.", "notice", JOptionPane.WARNING_MESSAGE);
                    humanPlayer.addChips(chips);
                }
                else{
                    JOptionPane.showMessageDialog(null, "당신이 졌습니다.", "notice", JOptionPane.WARNING_MESSAGE);
                }
                gameScreen.ResetField();
            }
        });

        END.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginScreen.SaveGame(gameScreen.getPlayer().getChips());
                System.exit(0);
            }
        });
    }

    public void NewGame(){
        humanPlayer.resetHand();
        computerPlayer.resetHand();
        computerPlayer = gameScreen.getDealer();
        humanPlayer = gameScreen.getPlayer();

        DEAL.setEnabled(false);
        HIT.setEnabled(true);
        STAND.setEnabled(true);

        //deal 이 완료되면 각 플레이어가 카드 2장씩 받음
        Card card;
        for (int i=0;i<2;i++) {
            card = cardDeck.newCard();
            computerPlayer.receiveCard(card);
            gameScreen.ShowCards(i,false,card);
            card = cardDeck.newCard();
            humanPlayer.receiveCard(card);
            gameScreen.ShowCards(i,true,card);
        }

        //플레이어의 패가 블랙잭일경우 플레이어의 승리로 게임 종료
        if (humanPlayer.getScore() == 21){
            gameScreen.RevealDealerCard();
            JOptionPane.showMessageDialog(null, "블랙잭! 당신이 이겼습니다.", "notice", JOptionPane.WARNING_MESSAGE);
            gameScreen.ResetField();
            humanPlayer.addChips(chips*5/2);
        }
    }

    public static void main(String[] args) {
        Play play = new Play();
        play.BlackJack();
    }

}
