package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import engine.Login;
import entity.HumanPlayer;

public class LoginScreen extends Screen{

    private GameScreen game;
    private RankScreen rank;
    private Login login;
    private String user_id;
    private String user_pass;
    private JPanel main;
    private JTextField userText;
    private JTextField passText;

    public LoginScreen(boolean isRun,GameScreen gameScreen) {
        super(0,400,300, isRun);
        game = gameScreen;
        rank = new RankScreen();
        login = new Login();
        main = new JPanel();
        this.container.add(main, BorderLayout.NORTH);
        main.setPreferredSize(new Dimension(200, 100));
        main.setBackground(Color.white);
        main.setLayout(null);
        this.frame.setTitle("login");
        this.frame.setLocation(550, 200);
        container.add(main);
        AddLabel();
        AddButton();
    }
    private void AddLabel(){
        JLabel title = new JLabel();
        title.setIcon(new ImageIcon("res/login.png"));
        title.setBounds(0, 0, 394, 100);
        main.add(title);

        JLabel idLabel = new JLabel("ID");
        idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        idLabel.setBounds(38, 113, 80, 25);
        main.add(idLabel);

        JLabel passLabel = new JLabel("PW");
        passLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        passLabel.setBounds(38, 163, 80, 25);
        main.add(passLabel);
        userText = new JTextField(20);
        userText.setBounds(132, 113, 160, 25);
        main.add(userText);

        passText = new JPasswordField(20);
        passText.setBounds(132, 163, 160, 25);
        main.add(passText);
    }

    public void AddButton(){
        JButton btnRank = new JButton("Rank");
        btnRank.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        btnRank.setBounds(74, 200, 100, 35);
        main.add(btnRank);
        btnRank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { rank.topUsers(); }
        });

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        btnLogin.setBounds(221, 200, 100, 35);
        main.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_id = userText.getText();
                user_pass = passText.getText();
                int result = login.LoginCheck(user_id,user_pass);
                switch (result) {
                    case -1 : {
                        JOptionPane.showMessageDialog(null, "ERROR");
                        break;
                    }
                    case 0 : {
                        JOptionPane.showMessageDialog(null, user_id+"로 로그인 합니다.");
                        game.setPlayer(new HumanPlayer(11));
                        game.getPlayer().setID(user_id);
                        game.getPlayer().setChips(login.getUserChip());
                        game.ShowChips();
                        isRunning = false;
                        game.SetIsRunning(true);
                        ViewScreen();
                        game.ViewScreen();
                        break;
                    }
                    case 1 : {
                        JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다.");
                        break;
                    }
                    case 2 : {
                        JOptionPane.showMessageDialog(null, "존재하지않는 아이디입니다.");
                        break;
                    }
                    case 3 : {
                        JOptionPane.showMessageDialog(null, "특수문자가 포함되어있습니다.");
                        break;
                    }
                    case 4 : {
                        JOptionPane.showMessageDialog(null, "칩이 없습니다.");
                        break;
                    }
                }
            }
        });
    }

    public void SaveGame(int chips){
        login.ChangeChip(chips);
    }

}